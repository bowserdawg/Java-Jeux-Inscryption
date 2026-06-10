package cards.pouvoir;

import cards.Card;
import cards.animals.AnimalCard;
import logic.Board;

public class ContactMortel extends Pouvoir {

    @Override
    public String getNom() {
        return "Contact Mortel";
    }

    @Override
    public String getDescription() {
        return "S'il inflige des dégâts à une autre créature (donc pas à un obstacle), la créature blessée meurt.";
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
        if (originalDamage > 0 && victim instanceof AnimalCard) {
            // Le retourner de sorte à ce qu'il meure (vie < 0)
            return Math.max(originalDamage, victim.getLife() + 1);
        }
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
