package logic;

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
    }
    
}
