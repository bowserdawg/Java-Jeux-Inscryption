package cards.pouvoir;

import cards.Card;
import logic.Board;

public class NombreusesVies extends Pouvoir {
    
    @Override
    public String getNom() {
        return "Nombreuses Vies";
    }

    @Override
    public String getDescription() {
        return "Reste vivant sur le plateau lorsqu'elle est sacrifiée.";
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
        // Reste vivant sur le plateau
        return true;
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
        return originalAttack;
    }
}
