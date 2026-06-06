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

    public void promptAction() {
        System.out.print("Votre choix : ");
        String input = s.nextLine();
        if (input.equalsIgnoreCase("fin")) {
        } else if (input.startsWith("placer ")) {
            String slotToPlace = input.substring("placer ".length(), 2);
            //comparer si l'emplacement est juste
//            if(slotToPlace )
//            slotToPlace
        }
    }
}
