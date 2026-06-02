package cards;

import cards.animals.AnimalCard;
import cards.animals.*;
import cards.interfaces.Attacker;
import logic.Board;
import logic.players.CardStack;
import logic.players.Player;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.*;

public class CardTest {
    
    @Test
    public void attackCard() {
        AnimalCard card1 = new Loup();
        AnimalCard card2 = new Loup();
        Board board = new Board();
        int lifeCard2Before = card2.getLife();
        card1.attack(board,card2);
        
        // On limite la vie 0 au minimum
        int cardAttaque;
        int expectedLife;
        if (card1.getAttaque().isPresent()) {
            cardAttaque = card1.getAttaque().get();
            expectedLife = lifeCard2Before - cardAttaque;
        } else {
            expectedLife = lifeCard2Before;
        }
        assertEquals(expectedLife, card2.getLife());
    }
    
    @Test
    public void drawNotEmptyTest() {
        ArrayList<AnimalCard> cards = new ArrayList<>();
        cards.add(new Loup());
        cards.add(new Hermine());
        cards.add(new Grizzly());
        CardStack stack = new CardStack(cards);
        ArrayList<AnimalCard> remainingCards = stack.getCards();
        Optional<AnimalCard> tempCard = stack.drawCard();
        AnimalCard card1;
        assertTrue(tempCard.isPresent());
        card1 = tempCard.get();
        assertFalse(remainingCards.contains(card1));
    }
    
    @Test
    public void drawEmptyTest() {
        ArrayList<AnimalCard> cards = new ArrayList<>();
        CardStack stack = new CardStack(cards);
        
        assertTrue(stack.getCards().isEmpty());
        Optional<AnimalCard> tempCard = stack.drawCard();
        assertTrue(tempCard.isEmpty());
    }
}

