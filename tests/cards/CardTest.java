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
        CardStack stack = new CardStack();
        AnimalCard card1 = stack.drawCard();
        AnimalCard card2 = stack.drawCard();
        
        int lifeCard2Before = card2.getLife();
        card1.attack(card2);
        // On limite la vie 0 au minimum
        int expectedLife = Math.max(lifeCard2Before- card1.getAttaque().get(), 0);
        assertEquals(expectedLife, card2.getLife());
    }
}

