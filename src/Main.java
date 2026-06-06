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
        Board board = new Board();
        Input i = new Input();
        
        ArrayList<AnimalCard> cards = new ArrayList<>();
        cards.add(new Loup());
        cards.add(new Chat());
        
        CardStack rivalStack = new CardStack(cards);
        CardStack userStack = new CardStack(cards);
        
        System.out.println(cards);
        
        User user = new User(userStack, board);
        Rival rival = new Rival(rivalStack, board);
        BoardDisplayLogic d = new BoardDisplayLogic(board, user, rival);
        
        user.placeCard(0, 0);
        System.out.println(cards);
        
        rival.placeCard(0,1);
        System.out.println(cards);
        
        d.afficher();
        board.passTurn();
        d.afficher();
        i.promptAction();
    }
}