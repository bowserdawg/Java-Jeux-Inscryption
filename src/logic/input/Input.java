package logic.input;

import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    static Scanner s = new Scanner(System.in);
    private final Controller m_controller;
    private int m_slot;
    private int m_cardToPlace;
private boolean m_alreadyDraw = false;

    public Input(Controller controller) {
        m_controller = controller;
    }

    /**
     *
     */

    public void promptAction() {
        System.out.println("[placer] Placer une carte sur le plateau");
        System.out.println("[piocher] Piocher une carte");
        System.out.println("[ecureuil] Piocher un écureuil");
        System.out.println("[fin] Finir son tour");
        System.out.println("[quitter] Quitter le jeu");
        System.out.print("Votre choix : ");
        String[] input = getUserInput();
        System.out.println(input[0]);
        if (input[0].equalsIgnoreCase("placer")) {
            promptCardToPlace();
        } else if (input[0].equalsIgnoreCase("piocher")) {
            m_controller.drawCard();
            System.out.println(input[0]);
        } else if (input[0].equalsIgnoreCase("ecureuil")) {
            m_controller.drawSquirrel();
        } else if (input[0].equalsIgnoreCase("fin")) {
            m_controller.passTurn();
        } else if (input[0].equalsIgnoreCase("quitter")) {
            m_controller.endGame();

        }
    }

    public void promptSacrifice() {
        ArrayList<Integer> cardsSelected = new ArrayList<>();
        int sacrificeNeeded = m_controller.costOf(m_cardToPlace);
        System.out.println("Sélectionnez les cartes à sacrifier une par une (1-4) ");
        int input;
        do {
            System.out.print("Carte " + cardsSelected.size() + "/" + sacrificeNeeded + " : ");
            input = getNumberInput();
            if (input >= 0 && input <= 4 && m_controller.isSacrifiable(input)) {
                cardsSelected.add(input);
            }
        }
        while (cardsSelected.size() < sacrificeNeeded);

        m_controller.sacrificeCards(cardsSelected);
        promptSlotToPlace(true);
    }

    public void promptCardToPlace() {
        System.out.print("Quelle carte placer ? : ");
        m_cardToPlace = getNumberInput();
        // si la carte est gratuit alors demander où placer la carte
        if (!m_controller.isCardFree(m_cardToPlace)) {

            // si ne peut pas sacrifier alors demander, changer de carte
            if (m_controller.canSacrifice(m_cardToPlace)) {
                promptSacrifice();
            } else {
                System.out.println("Pas assez de carte à sacrifier");
                promptCardToPlace();
            }
        } else {
            promptSlotToPlace(false);
        }

    }

    public void promptSlotToPlace(boolean hasSacrified) {
        System.out.print("Dans quelle case ? : ");
        m_slot = getNumberInput();
        if (hasSacrified) {
            m_controller.placeCard(m_slot, m_cardToPlace);
        } else {

            switch (m_controller.canPlace(m_cardToPlace)) {
                case NOT_ENOUGH_BLOOD:
                    System.out.println("Pas assez de gout de sang");
                    promptSlotToPlace(false);
                    break;
                case NOT_ENOUGH_BONES:
                    System.out.println("Pas assez d'os");
                    promptSlotToPlace(false);
                    break;
                case OK:
                    m_controller.placeCard(m_slot, m_cardToPlace);
                    break;
            }
        }
    }

    public String[] getUserInput() {
        String input;
        do {
            input = s.nextLine();
        }
        while (input.isEmpty());
        return input.split(" ");
    }

    public int getNumberInput() {

        boolean isNumber = false;
        int inputNumber = -1;
        do {

            try {
                String[] strInput = getUserInput();
                inputNumber = Integer.parseInt(strInput[0]);
                isNumber = true;
            } catch (NumberFormatException error) {
                System.out.println("Pas un nombre");
            }
        }
        while (!isNumber);
        return inputNumber - 1;
    }
}
