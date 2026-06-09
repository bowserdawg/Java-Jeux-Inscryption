package cards.animals;

import cards.Card;
//import cards.interfaces.Attacker;
import cards.donnes.CardDonne;
import cards.interfaces.*;
import cards.types.Typable;
import cards.types.Volant;
import logic.Board;

import javax.swing.text.html.Option;
import java.util.Optional;

public abstract class AnimalCard extends Card implements Attacker {

    public AnimalCard(CardDonne donne) {
        super(donne);
    }


    public void attack(Board board, Card card) {
        int attDmg = 0;
        // si la carte qu'on nous demande d'attaqué n'est null
        // sinon on recupère les nos degats
        Optional<Integer> att = getAttaque();
        if (att.isPresent()) {
            attDmg = att.get();
        }
        if (card != null) {
            card.receiveDamage(attDmg);
        } else {
            // sinon, on attaque l'adversaire
            board.attack(attDmg);
        }
    }

    @Override
    public boolean isSacrifiable() {
        return true;
    }
}