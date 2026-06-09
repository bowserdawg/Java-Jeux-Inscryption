package logic.input;

import display.BoardDisplayLogic;
import logic.Board;
import logic.GameStatus;

import java.util.Scanner;

public class Input {
    
    static Scanner s = new Scanner(System.in);
    private final Controller m_controller;
    
    
    public Input(Controller controller) {
        m_controller = controller;
    }
    
    /**
     *
     */
    
    public void promptAction() {
        System.out.println("[placer <numero carte> <position>] Placer une carte sur le plateau");
        System.out.println("[piocher] Piocher une carte");
        System.out.println("[ecureuil] Piocher un écureuil");
        System.out.println("[fin] Finir son tour");
        System.out.println("[quitter] Quitter le jeu");
        System.out.print("Votre choix : ");
        String[] input = s.nextLine().split(" ");
        System.out.println(input[0]);
        if (input.length == 3) {
            if (input[0].equalsIgnoreCase("placer")) {
                int cardIndex;
                int column;
                if (input[1].length() == 1) {
                    try {
                        cardIndex = Integer.parseInt(input[1]) - 1;
                        column = Integer.parseInt(input[2]) - 1;
                        m_controller.placeCard(column, cardIndex);
                    } catch (NumberFormatException exception) {
                        System.out.println("pas chiffre");
                    }
                }
            }
            
        } else if (input.length == 1) {
            if (input[0].equalsIgnoreCase("piocher")) {
                m_controller.drawCard();
                System.out.println(input[0]);
            } else if (input[0].equalsIgnoreCase("ecureuil")) {
                m_controller.drawSquirrel();
            } else {
                if (input[0].equalsIgnoreCase("fin")) {
                    m_controller.passTurn();
                }
            }
        }
    }
    
    public void promptSacrifie(){
        System.out.print("Sélectionnez les cartes à sacrifier (1-4 séparé par des espaces) : ");
        String[] input = s.nextLine().split(" ");
        int[] cardSelected = new int[input.length];
        for(int i = 0; i < input.length; i++){
            
            int card = Integer.parseInt(input[i]);
            cardSelected[i] = card;
        }
        m_controller.sacrificeCards(cardSelected);
    }
}
