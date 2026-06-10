package cards.pouvoir;

import cards.Card;
import cards.animals.Loup;
import logic.Board;

public class Croissance extends Pouvoir {
    private int turnsOnBoard = 0;

    @Override
    public String getNom() {
        return "Croissance";
    }

    @Override
    public String getDescription() {
        return "Se transforme en loup au début du deuxième tour, où il est sur le plateau.";
    }

    @Override
    public void onTurnStart(Board board, Card card, int row, int col) {
        turnsOnBoard++;
        if (turnsOnBoard >= 2) {
            Board.PositionsRow posRow = Board.PositionsRow.values()[row];
            board.removeCard(posRow, col);
            board.placeCard(posRow, col, new Loup());
        }
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
        return originalAttack;
    }
}
