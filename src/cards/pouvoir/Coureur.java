package cards.pouvoir;

import cards.Card;
import logic.Board;

public class Coureur extends Pouvoir {

    @Override
    public String getNom() {
        return "Coureur";
    }

    @Override
    public String getDescription() {
        return "Se déplace vers d'un emplacement vers la droite après son attaque. Si l'emplacement vers la droite est bloqué, se déplace vers la gauche.";
    }

    @Override
    public void onTurnStart(Board board, Card card, int row, int col) {
        // Pas d'action au début du tour
    }

    @Override
    public void onAfterAttack(Board board, Card card, int row, int col) {
        Board.PositionsRow posRow = Board.PositionsRow.values()[row];
        int rightCol = col + 1;
        if (rightCol < board.getWidth() && !board.isOccupied(posRow, rightCol)) {
            board.removeCard(posRow, col);
            board.placeCard(posRow, rightCol, card);
        } else {
            int leftCol = col - 1;
            if (leftCol >= 0 && !board.isOccupied(posRow, leftCol)) {
                board.removeCard(posRow, col);
                board.placeCard(posRow, leftCol, card);
            }
        }
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
        return originalAttack;
    }
}
