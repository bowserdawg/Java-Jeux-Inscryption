package logic;

import cards.animals.*;
import logic.players.CardStack;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;

import javax.swing.text.Position;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class BoardTest {
    
    @Test
    public void initBoard() {
        GameInit game = new GameInit();
        Board board = game.getBoard();
        // Test de la longeur du plateau
        assertEquals(4, board.getWidth());
        
        // Test de la hauteur du plateau
        assertEquals(3, board.getHeight());
        
        // Un plateau est de 4x3 par défaut
    }
    
    @Test
    public void placeFreeSlot() {
        GameInit game = new GameInit();
        Board board = game.getBoard();

        User user = game.getUser();

        assertTrue(user.placeCard(0, 0));
    }
    
    @Test
    public void placeOccupiedSlot() {
        GameInit game = new GameInit();
        Board board = new Board();

        User user = game.getUser();
        ArrayList<AnimalCard> userHand = new ArrayList<>(user.getHand());

        assertTrue(user.placeCard(0, 0));
        assertFalse(user.placeCard(0, 0));
        assertEquals(userHand.size() - 1, user.getHand().size());
    }
    
    @Test
    public void playerTurnTest() {
        Board board = new Board();
        assertFalse(board.isPlayerTurn());
        board.passTurn();
        assertTrue(board.isPlayerTurn());
    }
    
    @Test
    public void passTurnTest() {
        playerTurnTest();
        GameInit game = new GameInit();
        Board board = game.getBoard();
        AnimalCard card = new Loup();
        board.placeCard(Board.Positions.NextRivalRow, 0, card);
        
        assertEquals(board.getCard(Board.Positions.NextRivalRow, 0), card);
        
        assertFalse(board.isOccupied(Board.Positions.RivalRow, 0));
        assertEquals(board.getCard(Board.Positions.NextRivalRow, 0), card);
        board.passTurn();
        
        assertEquals(board.getCard(Board.Positions.RivalRow, 0), card);
        assertFalse(board.isOccupied(Board.Positions.NextRivalRow, 0));
    }
    
    @Test
    public void cardsNoDamageAttack() {
        GameInit game = new GameInit();

        Board board = game.getBoard();
        AnimalCard wolf = new Loup();
        AnimalCard squirrel = new Ecureuil();
        
        assertEquals(1, squirrel.getLife());
        assertEquals(2, wolf.getLife());
        board.placeCard(Board.Positions.UserRow, 0, squirrel);
        board.placeCard(Board.Positions.RivalRow, 0, wolf);
        board.passTurn();
        assertEquals(1, squirrel.getLife());
        board.passTurn();
        assertFalse(board.isOccupied(Board.Positions.RivalRow, 0));
        assertTrue(board.isOccupied(Board.Positions.UserRow, 0));
        
        assertEquals(-2, squirrel.getLife());
        assertEquals(2, wolf.getLife());
    }
    
    @Test
    public void damagePropagationTest() {
        GameInit game = new GameInit();

        Board board = game.getBoard()
        Loup wolf = new Loup();
        Ecureuil backSquirrel = new Ecureuil();
        Ecureuil frontSquirrel = new Ecureuil();
        
        board.placeCard(Board.Positions.UserRow,0,wolf);
        board.placeCard(Board.Positions.RivalRow, 0,frontSquirrel);
        board.placeCard(Board.Positions.NextRivalRow,0, backSquirrel);
        
        assertEquals(1, frontSquirrel.getLife());
        assertEquals(1, backSquirrel.getLife());
        assertEquals(2, wolf.getLife());
        
        assertEquals(2, board.passTurn());
        
        assertEquals(-1, backSquirrel.getLife());
        assertEquals(-2, frontSquirrel.getLife());
        assertEquals(2, wolf.getLife());
    }
}
    
    
