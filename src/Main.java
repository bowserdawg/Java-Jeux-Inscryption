import display.BoardDisplayLogic;
import logic.GameInit;
import logic.input.Controller;
import logic.input.Input;
import logic.GameStatus;


public class Main {
    public static void main(String[] args) {
        System.out.println("Another challenger... It has been ages.");
        
        // initialisation d'une partie
        GameInit game = new GameInit(); // utilisation d'une classe pour facilement initialiser une partie
        Input i = game.getInput();
        Controller controller = game.getUserController();
        BoardDisplayLogic display = game.getDisplay();
        display.afficher();
        boolean gameRunning = true;
        while (gameRunning) {
            GameStatus gameStatus = controller.getGameStatus();
            switch (gameStatus) {
                case PLACED:
                    display.afficher();
                    break;
                case NEED_TO_SACRIFIE:
                    i.promptSacrifie();
                case STOPPED:
                    gameRunning = false;
                    break;
                case CANT_PLACE:
                    switch (controller.getPlacingReason()) {
                        case NOT_ENOUGH_BLOOD:
                            System.out.println("pas assez de cartes à sacrifier");
                            break;
                        case NOT_ENOUGH_BONES:
                            System.out.println("pas de assez d'os");
                    }
                    break;
                case PASSED_TURN:
                    game.getRival().autoPlaceCard();
                    display.afficher();
                    break;
            }
            i.promptAction();
        }
    }
}