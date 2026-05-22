import cards.Card;
import display.Display;
import logic.Board;
import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Another challenger... It has been ages.");
// initialisation d'une partie;
        Board board = new Board();
        CardStack rivalStack = new CardStack();
        CardStack userStack = new CardStack();
        
        User user = new User(userStack, board);
        Rival rival = new Rival(rivalStack, board);
        
            user.placeCard(3, 0);
        
        Display display = new Display();
        display.draw(board);
        
        
        
        
    }
}