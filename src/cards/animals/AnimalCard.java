package cards.animals;

import cards.Card;
//import cards.interfaces.Attacker;
import cards.donnes.CardDonne;
import cards.interfaces.*;
import cards.types.Typable;
import cards.types.Volant;
import cards.pouvoir.Pouvoir;
import logic.Board;

import javax.swing.text.html.Option;
import java.util.Optional;

public abstract class AnimalCard extends Card implements Attacker {

    public AnimalCard(CardDonne donne) {
        super(donne);
    }


    public void attack(Board board, Card card) {
        int attDmg = 0;
        // si la carte qu'on nous demande d'attaqué n'est pas nulle,
        // sinon on récupère nos dégâts
        Optional<Integer> att = getAttaque();
        if (att.isPresent()) {
            attDmg = att.get();
        }
        if (card != null) {
            // Appliquer Puant de la victime (si présente) pour réduire l'attaque
            for (Pouvoir p : card.getPouvoirs()) {
                attDmg = p.modifyAttackValue(attDmg);
            }
            // Appliquer Contact Mortel de l'attaquant
            for (Pouvoir p : this.getPouvoirs()) {
                attDmg = p.modifyDamageDealt(attDmg, card);
            }

            card.receiveDamage(attDmg);

            // Appliquer Piques Pointues de la victime
            for (Pouvoir p : card.getPouvoirs()) {
                p.modifyDamageReceived(attDmg, this);
            }
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