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
        game.getRival().autoPlaceCard();
        display.afficher();
        boolean gameRunning = true;
        while (gameRunning) {
            GameStatus gameStatus = controller.getGameStatus();
            switch (gameStatus) {
                case REFRESH:
                    display.afficher();
                    break;
                case STOPPED:
                    gameRunning = false;
                    break;
                case USER_WIN:
                    System.out.println("Le joueur à gagner");
                    game.newPartie();
                    break;
                case RIVAL_WIN:
                    System.out.println("L'adversaire à gagner");
                    game.newPartie();
                   

                default:
                    System.out.println("Unreachable");
                    break;
            }
            if(game.getBoard().isPlayerTurn()){
                i.promptAction();
            }
            else {
                game.getRival().autoPlaceCard();
                game.getBoard().passTurn();
            }
        }
    }
}