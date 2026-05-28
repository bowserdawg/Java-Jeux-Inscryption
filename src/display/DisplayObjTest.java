package display;

import display.interfaces.Displayable;

public class DisplayObjTest implements Displayable {
    @Override
    public char[][] getDisplayableFormArray() {

        return new char[][]{
                {'|', 'h', 'e', 'l'},
                {'|', 'h', 'l', 'o'},
                {'w', 'o', 'r', 'd'}
        };
    }
}
