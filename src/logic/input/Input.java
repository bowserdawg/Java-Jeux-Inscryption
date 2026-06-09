package logic.input;

import cards.CostType;
import logic.CantPlaceReason;
import logic.DrawReason;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Input {

    static Scanner s = new Scanner(System.in);
    private final Controller m_controller;
    private int m_slot;
    private int m_cardToPlace;

    public Input(Controller controller) {
        m_controller = controller;
    }

    /**
     *
     */

    public void promptAction() {
        System.out.println("[placer] Placer une carte sur le plateau");
        System.out.println("[piocher] Piocher une carte");
        System.out.println("[fin] Finir son tour");
        System.out.println("[quitter] Quitter le jeu");
        System.out.print("Votre choix : ");
        String[] input = getUserInput();
        if (input[0].equalsIgnoreCase("placer")) {
            promptCardToPlace();
        } else if (input[0].equalsIgnoreCase("piocher")) {
            if (!m_controller.hasAlreadyDraw()) {
                promptDraw();
            }
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
            }else {
                System.out.println("Pas de cartes à sacrifier ici");
            }

        }
        while (cardsSelected.size() < sacrificeNeeded);

        m_controller.sacrificeCards(cardsSelected);
        promptSlotToPlace(true);
    }

    public void promptCardToPlace() {
        System.out.print("Quelle carte placer ? : ");
        m_cardToPlace = getNumberInput();
        // si la carte n'est pas gratuite alors
        if (!m_controller.isCardFree(m_cardToPlace)) {
            // voir si la carte coute du sang
            CostType costTypeCard = m_controller.getCostType(m_cardToPlace);
            if (costTypeCard == CostType.BLOOD_DROPS) {
                // et si on peut sacrifier des cartes pour la placer alors
                if (m_controller.getNbSacrifiableCards() >= m_controller.costOf(m_cardToPlace)) {
                    // demander les cartes à sacrifier
                    promptSacrifice();
                } else {
                    System.out.println("Pas assez de carte à sacrifier");
                    promptAction();
                }
                //si la carte coute des os
            } else if (costTypeCard == CostType.BONES) {
                // et qu'on a assez d'os
                if (m_controller.costOf(m_cardToPlace) <= m_controller.getBones()) {
                    promptSlotToPlace(false);
                }else {
                    System.out.println("Pas assez d'os");
                    promptAction();
                }
            }
        }
        // si la carte est gratuite alors demander où la placer
        else {
            promptSlotToPlace(false);
        }
    }

    public void promptDraw() {
        System.out.println("Que voulez vous piochez ? ");
        System.out.println("1. Carte normale");
        System.out.println("2. Carte Ecureuil");
        System.out.println("Votre choix (1-2) :");
        int input;
        do {
            input = getNumberInput();

            if (input == 0) {
                if (m_controller.drawCard() != DrawReason.OK) {
                    return;
                }
            } else if (input == 1) {
                if (m_controller.drawSquirrel() != DrawReason.OK) {
                    return;
                }
            } else {
                System.out.println("Veuillez retentez");
            }
        }
        while (!(input >= 0 && input <= 1));
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
