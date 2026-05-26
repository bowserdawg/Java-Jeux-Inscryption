import display.BoardPlaceHolder;
import display.Display;
import display.DisplayObjTest;
import display.NewDisplay;
import logic.Board;
import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Another challenger... It has been ages.");

        // initialisation d'une partie
        Board board = new Board();
        CardStack rivalStack = new CardStack();
        CardStack userStack = new CardStack();
        
        User user = new User(userStack, board);
        Rival rival = new Rival(rivalStack, board);
        
            user.placeCard(3, 0);


        Display display = new Display();

        NewDisplay ndisplay = new NewDisplay();

        ndisplay.printRow("Hello World", 2, 89);
        char[] arr = {'h', 'e', 'l', 'l', 'o'};
        ndisplay.printRow(arr, 3, 89);

        DisplayObjTest d = new DisplayObjTest();

        ndisplay.draw(d, 7, 95);

        ndisplay.printBuffer();

        //display.draw(board);

        BoardPlaceHolder bph = new BoardPlaceHolder();

        // Méthode temporaire pour avoir des cartes.
        bph.setCardsTemp();
        //display.draw(bph);
        
    }
}