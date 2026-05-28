package cards.animals;

import cards.Card;
//import cards.interfaces.Attacker;
import cards.donnes.CardDonne;
import cards.interfaces.*;

public abstract class AnimalCard extends Card implements Attacker {

    public AnimalCard(CardDonne donne) {
        super(donne);
    }

    @Override
    public void attack(Card card) {

        var att = getAttaque();

        if(att.isEmpty())
            throw new RuntimeException("La carte " + this + " ne peut pas attaquer");

        card.receiveDamage(att.get());
    }
}