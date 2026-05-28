package cards;

import cards.animals.AnimalCard;
import cards.interfaces.Attacker;
import logic.Board;
import logic.players.CardStack;
import logic.players.Player;
import logic.players.Rival;
import logic.players.User;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class CardTest {
    
    @Test
    public void attackCard() {
        AnimalCard card1 = new Loup();
        AnimalCard card2 = new Loup();
        int lifeCard2Before = card2.getLife();
        card1.attack(card2);
        
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
}

