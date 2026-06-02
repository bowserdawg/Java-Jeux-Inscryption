package display;

import cards.Card;
import cards.animals.Chat;
import cards.animals.Ecureuil;
import cards.animals.Moineau;
import cards.obstacles.Pin;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class DisplayTest {

    @Test
    public void testDisplayVide()
    {
        Display display = new Display();
        display.printBufferToScreen();
    }

    @Test
    public void testDisplayObj()
    {
        Display display = new Display();
        DisplayObjTest obj = new DisplayObjTest();
        display.draw(obj, 0, 0);
        display.printBufferToScreen();
    }

    @Test
    public void erreurTropGrand()
    {
        Display display = new Display();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> display.printRow("Hello", 0, 120)
        );

        display.printBufferToScreen();
    }

    @Test
    public void erreurTropGrand2()
    {
        Display display = new Display(42, 120);

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> display.printRow("Hello", 45, 0)
        );

        display.printBufferToScreen();
    }

    public void erreurNegativeLen()
    {
        Display display = new Display(-1, 120);

        display.printBufferToScreen();
    }

    @Test
    public void overWritePrint()
    {
        Display display = new Display();

        display.printRow("Hello", 0, 0);

        display.printRow("World", 0, 0);

        var row1 = display.getRow(0);

        assert(row1[0] == 'W' && row1[4] == 'd');
    }

    @Test
    public void clearTest()
    {
        Display display = new Display();

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
        Display display = new Display(24, 120);

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
