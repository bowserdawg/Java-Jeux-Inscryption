package logic;

import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    
    @Test
    public void initBoard(){
        Board board = new Board();
        // Test de la longeur du plateau
        assertEquals(4, board.getWidth());
        
        // Test de la hauteur du plateau
        assertEquals(3, board.getHeight());
        
        // Un plateau est de 4x3 par défaut
    }
    
    @Test
    public void placeFreeSlot(){
        Board board = new Board();
        CardStack userStack = new CardStack();
        
        User user = new User(userStack, board);
        
        
        
        boolean result = user.placeCard(0, 0);
        
        
        assertEquals(true, result);
    }
    
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
    
}
