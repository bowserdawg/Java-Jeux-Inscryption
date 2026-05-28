package logic;

import cards.animals.*;
import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {
    
    @Test
    public void initBoard() {
        Board board = new Board();
        // Test de la longeur du plateau
        assertEquals(4, board.getWidth());
        
        // Test de la hauteur du plateau
        assertEquals(3, board.getHeight());
        
        // Un plateau est de 4x3 par défaut
    }
    
    @Test
    public void placeFreeSlot() {
        Board board = new Board();
        ArrayList<AnimalCard> cards = new ArrayList<>();
        cards.add(new Loup());
        cards.add(new Chat());
        cards.add(new Hermine());
        
        CardStack userStack = new CardStack(cards);
        
        User user = new User(userStack, board);
        
        boolean result = user.placeCard(0, 0);
        
        assertEquals(true, result);
    }
    
    @Test
    public void placeOccupiedSlot() {
        Board board = new Board();
        
        ArrayList<AnimalCard> cards = new ArrayList<>();
        cards.add(new Loup());
        cards.add(new Chat());
        cards.add(new Hermine());
        
        CardStack userStack = new CardStack(cards);
        
        User user = new User(userStack, board);
        ArrayList<AnimalCard> userHand = new ArrayList<>(user.getHand());
        assertTrue(user.placeCard(0, 0));
        boolean result = user.placeCard(0, 0);
        assertFalse(result);
        assertEquals(userHand.size()-1, user.getHand().size());
        
        
    }
    
    
}
