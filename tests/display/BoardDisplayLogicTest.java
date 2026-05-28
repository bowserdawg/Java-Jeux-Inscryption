package display;

import org.junit.Test;

public class BoardDisplayLogicTest {

    @Test
    public void testNbPartie()
    {
        BoardPlaceHolder b = new BoardPlaceHolder();
        b.setCardsTemp();

        BoardDisplayLogic log = new BoardDisplayLogic(b);

        log.afficher();
    }

    @Test
    public void testAffichage()
    {
        BoardPlaceHolder b = new BoardPlaceHolder();
        b.setCardsTemp();

        BoardDisplayLogic log = new BoardDisplayLogic(b);

        log.afficher();
    }

}
