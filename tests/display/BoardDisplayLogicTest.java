package display;

import logic.Board;
import logic.players.CardStack;
import logic.players.Player;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;

public class BoardDisplayLogicTest {

    @Test
    public void testNbPartie()
    {
        Board b = new Board();
        b.setCards();

        Rival rival = new Rival(new CardStack(), b);
        User user = new User(new CardStack(), b);

        BoardDisplayLogic log = new BoardDisplayLogic(b, user, rival);

        log.afficher();
    }
    @Test
    public void testAffichage()
    {
        Board b = new Board();
        b.setCards();

        Rival rival = new Rival(new CardStack(), b);
        User user = new User(new CardStack(), b);

        BoardDisplayLogic log = new BoardDisplayLogic(b, user, rival);

        log.afficher();
    }

}
