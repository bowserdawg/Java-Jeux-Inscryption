package cards;

import cards.animals.*;
import cards.pouvoir.*;
import logic.Board;
import org.junit.Test;
import static org.junit.Assert.*;

public class PouvoirTest {

    @Test
    public void testNombreusesVies() {
        Board board = new Board();
        Chat cat = new Chat();
        board.placeCard(Board.PositionsRow.UserRow, 0, cat);
        
        // Sacrifier le chat
        assertTrue(board.isCardSacrifiable(Board.PositionsRow.UserRow, 0));
        
        // Le chat doit toujours être présent sur le plateau !
        assertNotNull(board.getCard(Board.PositionsRow.UserRow, 0));
        assertEquals("Chat", board.getCard(Board.PositionsRow.UserRow, 0).getName());
        
        // Et on doit avoir gagné un os
        assertEquals(1, board.getRemainingBones(logic.BoneProperties.USER));
    }

    @Test
    public void testCroissance() {
        Board board = new Board();
        Louveteau cub = new Louveteau();
        board.placeCard(Board.PositionsRow.UserRow, 0, cub);
        
        // Premier tour (cub est placé)
        board.passTurn(); 
        assertEquals("Louveteau", board.getCard(Board.PositionsRow.UserRow, 0).getName());
        
        board.passTurn(); 
        assertEquals("Louveteau", board.getCard(Board.PositionsRow.UserRow, 0).getName());
        
        board.passTurn(); // tour 2 (UserRow) -> se transforme en Loup !
        assertEquals("Loup", board.getCard(Board.PositionsRow.UserRow, 0).getName());
    }

    @Test
    public void testPuant() {
        Board board = new Board();
        Punaise stinky = new Punaise(); 
        Loup wolf = new Loup(); 
        
        board.placeCard(Board.PositionsRow.RivalRow, 0, stinky);
        board.placeCard(Board.PositionsRow.UserRow, 0, wolf);
        
        // On force le tour du joueur
        board.passTurn();
        
        // Loup a 3 attaque, réduite à 2. Punaise a 2 PV, sa vie passe à 0 (meurt).
        assertEquals(0, stinky.getLife());
        assertNull(board.getCard(Board.PositionsRow.RivalRow, 0));
    }

    @Test
    public void testCoureur() {
        Board board = new Board();
        Elan runner = new Elan(); 
        board.placeCard(Board.PositionsRow.UserRow, 0, runner);
        
        board.passTurn(); 
        
        assertNull(board.getCard(Board.PositionsRow.UserRow, 0));
        assertNotNull(board.getCard(Board.PositionsRow.UserRow, 1));
        assertEquals("Elan", board.getCard(Board.PositionsRow.UserRow, 1).getName());
    }

    @Test
    public void testContactMortel() {
        Board board = new Board();
        Vipere viper = new Vipere(); 
        Grizzly grizzly = new Grizzly(); 
        
        board.placeCard(Board.PositionsRow.UserRow, 0, viper);
        board.placeCard(Board.PositionsRow.RivalRow, 0, grizzly);
        
        board.passTurn();
        
        // Grizzly meurt directement
        assertNull(board.getCard(Board.PositionsRow.RivalRow, 0));
    }

    @Test
    public void testPiquesPointues() {
        Board board = new Board();
        PorcEpic quill = new PorcEpic(); 
        Louveteau attacker = new Louveteau(); 
        
        board.placeCard(Board.PositionsRow.UserRow, 0, attacker);
        board.placeCard(Board.PositionsRow.RivalRow, 0, quill);
        
        board.passTurn();
        
        assertEquals(1, quill.getLife());
        assertEquals(0, attacker.getLife());
        assertNull(board.getCard(Board.PositionsRow.UserRow, 0));
    }
}
