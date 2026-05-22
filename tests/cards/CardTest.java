package cards;

import logic.Board;
import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CardTest {
    @Test
    public void placeOccupiedSlot(){
        Board board = new Board();
        CardStack userStack = new CardStack();
        CardStack rivalStack = new CardStack();
        
        Rival rival = new Rival(rivalStack, board);
        User user = new User(userStack, board);
        
        user.placeCard(0, 0);
        boolean result = user.placeCard(0, 0);
        assertEquals(false, result);
    }
    
    @Test
    public void placeFreeSlot(){
        Board board = new Board();
        CardStack userStack = new CardStack();
        
        User user = new User(userStack, board);
        
        
        
        boolean result = user.placeCard(0, 0);
        
        
        assertEquals(true, result);
    }
}
