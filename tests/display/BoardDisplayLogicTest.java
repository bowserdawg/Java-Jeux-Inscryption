package display;

import cards.animals.AnimalCard;
import cards.animals.Chat;
import cards.animals.Moineau;
import logic.Board;
import logic.players.CardStack;
import logic.players.Player;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;

import java.util.ArrayList;

public class BoardDisplayLogicTest {

    @Test
    public void testNbPartie()
    {
        Board b = new Board();
        b.placeCard(Board.Positions.NextRivalRow, 0, new Moineau());
        b.placeCard(Board.Positions.NextRivalRow, 1, new Chat());

        ArrayList<AnimalCard> cardStack = new ArrayList<>();

        Rival rival = new Rival(new CardStack(cardStack), b);
        User user = new User(new CardStack(cardStack), b);

        BoardDisplayLogic log = new BoardDisplayLogic(b, user, rival);

        log.afficher();
    }

    @Test
    public void testAffichage()
    {
        Board b = new Board();
        b.placeCard(Board.Positions.NextRivalRow, 0, new Moineau());
        b.placeCard(Board.Positions.NextRivalRow, 1, new Chat());

        ArrayList<AnimalCard> cardStack = new ArrayList<>();

        Rival rival = new Rival(new CardStack(cardStack), b);
        User user = new User(new CardStack(cardStack), b);

        BoardDisplayLogic log = new BoardDisplayLogic(b, user, rival);

        log.afficher();
    }

}
