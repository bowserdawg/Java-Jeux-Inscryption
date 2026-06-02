import display.BoardDisplayLogic;
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

        BoardDisplayLogic log = getBoardDisplayLogic();

        log.afficher();
    }

    private static BoardDisplayLogic getBoardDisplayLogic() {
        //display.draw(board);

        Board b = new Board();

        // Méthode temporaire pour avoir des cartes.
        b.setCards();
        //display.draw(bph);
        BoardDisplayLogic log = new BoardDisplayLogic(b,
                new User(new CardStack(), b),
                new Rival(new CardStack(), b)
        );
        return log;
    }
}