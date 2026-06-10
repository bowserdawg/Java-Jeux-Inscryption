package cards;

import cards.interfaces.Nommable;
import display.interfaces.Displayable;

public class CardNull implements Nommable, Displayable {
    @Override
    public String getName() {
        return "NullCard";
    }

    @Override
    public char[][] getDisplayableForm()
    {
        return
                new char[][]
                        {
                                "*************".toCharArray(),
                                "*           *".toCharArray(),
                                "*           *".toCharArray(),
                                "*           *".toCharArray(),
                                "*           *".toCharArray(),
                                "*           *".toCharArray(),
                                "*           *".toCharArray(),
                                "*************".toCharArray()
                        };
    }
}
