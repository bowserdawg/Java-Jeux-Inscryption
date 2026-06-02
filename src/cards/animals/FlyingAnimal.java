package cards.animals;

import cards.Card;
import cards.donnes.CardDonne;
import logic.Board;

import java.util.Optional;

public abstract class FlyingAnimal extends AnimalCard {
    
    public FlyingAnimal(CardDonne donnee) {
        super(donnee);
    }
    
    @Override
    public void attack(Board board, Card card) {
        int attDmg = 0;
        Optional<Integer> att = getAttaque();
        if (att.isPresent()) {
            attDmg = att.get();
        }
        board.attack(attDmg);
    }
}
