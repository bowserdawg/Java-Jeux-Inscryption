import cards.Card;
import cards.animals.*;
//import display.BoardPlaceHolder;
//import display.Display;
import logic.Board;
import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Another challenger... It has been ages.");

        // initialisation d'une partie
        Board board = new Board();

        ArrayList<AnimalCard> cards = new ArrayList<>();
        cards.add(new Loup());
        cards.add(new Chat());

        CardStack rivalStack = new CardStack(cards);
        CardStack userStack = new CardStack(cards);
        
        User user = new User(userStack, board);
        Rival rival = new Rival(rivalStack, board);

        user.placeCard(3, 0);
        user.placeCard(2, 0);

            board.passTurn();
    }
}