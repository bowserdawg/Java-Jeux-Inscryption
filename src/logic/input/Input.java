package logic.input;

import display.BoardDisplayLogic;
import logic.Board;

import java.util.Scanner;

public class Input {
    
    static Scanner s = new Scanner(System.in);
    private final Controller m_controller;
    
    
    public Input(Controller controller) {
        m_controller = controller;
    }
    
    /**
     *
     * @return False si le joueur demande la fin du jeu
     */
    
    public boolean promptAction() {
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
            } else {
                if (input[0].equalsIgnoreCase("fin")) {
                    m_controller.passTurn();
                }
            }
        }
        return true;
    }
}
