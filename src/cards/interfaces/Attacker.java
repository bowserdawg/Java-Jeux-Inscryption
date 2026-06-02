package cards.interfaces;

import cards.Card;
import logic.Board;

public interface Attacker {
    // Attack un autre animal
    void attack(Board board, Card card);
    
}
