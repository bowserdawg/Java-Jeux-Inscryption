package cards.pouvoir;

import cards.Card;
import logic.Board;

public class Puant extends Pouvoir {

    @Override
    public String getNom() {
        return "Puant";
    }

    @Override
    public String getDescription() {
        return "Réduit de 1 l'attaque de la carte lui faisant face.";
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
        return originalDamage;
    }

    @Override
    public int modifyAttackValue(int originalAttack) {
        // Le pouvoir Puant lui-même réduit l'attaque brute de l'adversaire de 1
        return Math.max(0, originalAttack - 1);
    }
}
