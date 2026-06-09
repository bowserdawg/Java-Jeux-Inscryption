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
                    game.getBoard().increasePartie();
                case RIVAL_WIN:
                    System.out.println("L'adversaire à gagner");
                    game.getBoard().increasePartie();
                    // faire dans game init une methode restart qui va remettre la pioche du joueur et de l'adversaire à zero ainsi que le score et le plateau
                    // s'il y a le temps ajouté une liste des cartes existante et choisir au hasard une carte à ajouter dans la pioche du joueur
                    // pour cela ne pas donner directement la liste de cartes par defaut mais faire une copy et ajouter la carte à la copie de la liste

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