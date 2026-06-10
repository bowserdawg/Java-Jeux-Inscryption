package cards.pouvoir;

import cards.Card;
import logic.Board;

public class PiquesPointues extends Pouvoir {

    @Override
    public String getNom() {
        return "Piques pointues";
    }

    @Override
    public String getDescription() {
        return "Inflige 1 point de dégât à la carte attaquante lorsqu'il est attaqué par une carte.";
    }

    @Override
    public void onTurnStart(Board board, Card card, int row, int col) {
        // Pas d'action au début du tour
    }

    @Override
    public void onAfterAttack(Board board, Card card, int row, int col) {
        // Pas d'action après l'attaque
    }

    @Override
    public boolean onSacrifice(Board board, Card card, int row, int col) {
        return false;
    }

    @Override
    public int modifyDamageDealt(int originalDamage, Card victim) {
        return originalDamage;
    }

    @Override
    public int modifyDamageReceived(int originalDamage, Card attacker) {
        if (attacker != null) {
            attacker.receiveDamage(1);
        }
        return originalDamage;
    }

    @Override
    public int modifyAttackValue(int originalAttack) {
        return originalAttack;
    }
}
