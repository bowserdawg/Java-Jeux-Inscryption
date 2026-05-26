package cards.animals;

import cards.Card;
//import cards.interfaces.Attacker;
import cards.donnes.CardDonne;
import cards.interfaces.*;

import java.util.Optional;

public abstract class AnimalCard extends Card implements Attacker {
    
    public AnimalCard(CardDonne donne) {
        super(donne);
    }

    @Override
    public void attack(Card card) {
        Optional<Integer> att = getAttaque();

        if(att.isEmpty())
            throw new RuntimeException("La carte " + this + " ne peut pas attaquer");

        card.receiveDamage(att.get());
    }
}

