import cards.Card;
import cards.animals.*;
//import display.BoardPlaceHolder;
//import display.Display;
import display.BoardDisplayLogic;
import display.Display;
import logic.Board;
import logic.input.Input;
import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Another challenger... It has been ages.");
        
        // initialisation d'une partie
        GameInit game = new GameInit(); // utilisation d'une classe pour facilement initialiser une partie

        Board board = game.getBoard();
        Input i = game.getInput();

        CardStack rivalStack = game.getRivalStack();
        CardStack userStack = game.getUserStack();

        User user = game.getUser();
        Rival rival = game.getRival();
        BoardDisplayLogic d = game.getDisplay();

        user.placeCard(0, 0);

        rival.placeCard(0,1);

        d.afficher();
        board.passTurn();
        d.afficher();
        i.promptAction();
    }
}