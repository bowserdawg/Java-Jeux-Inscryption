package display;

import cards.Card;
import cards.animals.Chat;
import cards.animals.Ecureuil;
import cards.animals.Hermine;
import cards.animals.Moineau;
import cards.donnes.CardDonne;
import cards.obstacles.Pin;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class NewDisplayTest {

    @Test
    public void testDisplayVide()
    {
        NewDisplay display = new NewDisplay();
        display.printBufferToScreen();
    }

    @Test
    public void testDisplayObj()
    {
        NewDisplay display = new NewDisplay();
        DisplayObjTest obj = new DisplayObjTest();
        display.draw(obj, 0, 0);
        display.printBufferToScreen();
    }

    @Test
    public void erreurTropGrand()
    {
        NewDisplay display = new NewDisplay();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> display.printRow("Hello", 0, 120)
        );

        display.printBufferToScreen();
    }

    @Test
    public void erreurTropGrand2()
    {
        NewDisplay display = new NewDisplay();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> display.printRow("Hello", 45, 0)
        );

        display.printBufferToScreen();
    }

    @Test
    public void overWritePrint()
    {
        NewDisplay display = new NewDisplay();

        display.printRow("Hello", 0, 0);

        display.printRow("World", 0, 0);

        var row1 = display.getRow(0);

        assert(row1[0] == 'W' && row1[4] == 'd');
    }

    @Test
    public void clearTest()
    {
        NewDisplay display = new NewDisplay();

        int i = 0;

        while(i < 10)
        {
            display.printRow("Hello", 0, 0);
            display.clearScreen();
            i++;
        }
        display.printBufferToScreen();
    }


    @Test
    public void testDisplayableCarte()
    {
        NewDisplay display = new NewDisplay();

        Card c1 = new Ecureuil();
        Card c2 = new Chat();

        Card c3 = new Pin();
        Card c4 = new Moineau();

        display.draw(c1, 0, 0);

        display.draw(c2, 0, 14);

        display.draw(c3, 0, 28);

        display.draw(c4, 0, 42);

        display.printBufferToScreen();
    }
}
