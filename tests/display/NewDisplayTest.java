package display;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertThrows;

public class NewDisplayTest {

    @Test
    public void testDisplayVide()
    {
        NewDisplay display = new NewDisplay();
        display.printBuffer();
    }

    @Test
    public void testDisplayObj()
    {
        NewDisplay display = new NewDisplay();
        DisplayObjTest obj = new DisplayObjTest();
        display.draw(obj, 0, 0);
        display.printBuffer();
    }

    @Test
    public void erreurTropGrand()
    {
        NewDisplay display = new NewDisplay();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> display.printRow("Hello", 0, 120)
        );

        display.printBuffer();
    }

    @Test
    public void erreurTropGrand2()
    {
        NewDisplay display = new NewDisplay();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> display.printRow("Hello", 45, 0)
        );

        display.printBuffer();
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

}
