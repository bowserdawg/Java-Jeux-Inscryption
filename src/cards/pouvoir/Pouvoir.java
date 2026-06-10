package cards.pouvoir;

import cards.Card;
import logic.Board;

public abstract class Pouvoir {
    
    /**
     * Retourne le nom du pouvoir.
     * @return Le nom du pouvoir.
     */
    public abstract String getNom();

    /**
     * Retourne la description du pouvoir.
     * @return La description du pouvoir.
     */
    public abstract String getDescription();

    /**
     * Déclenché au début du tour pour une carte présente sur le plateau.
     * @param board Le plateau de jeu.
     * @param card La carte possédant ce pouvoir.
     * @param row La ligne de la carte.
     * @param col La colonne de la carte.
     */
    public abstract void onTurnStart(Board board, Card card, int row, int col);

    /**
     * Déclenché après que la carte a effectué son attaque.
     * @param board Le plateau de jeu.
     * @param card La carte possédant ce pouvoir.
     * @param row La ligne de la carte.
     * @param col La colonne de la carte.
     */
    public abstract void onAfterAttack(Board board, Card card, int row, int col);

    /**
     * Déclenché lorsque la carte est sacrifiée.
     * @param board Le plateau de jeu.
     * @param card La carte possédant ce pouvoir.
     * @param row La ligne de la carte.
     * @param col La colonne de la carte.
     * @return true si le sacrifice est géré spécifiquement (ex: la carte reste sur le plateau), false sinon.
     */
    public abstract boolean onSacrifice(Board board, Card card, int row, int col);

    /**
     * Permet de modifier les dégâts infligés par cette carte à une victime.
     * @param originalDamage Les dégâts initiaux.
     * @param victim La carte victime de l'attaque.
     * @return Les dégâts modifiés.
     */
    public abstract int modifyDamageDealt(int originalDamage, Card victim);

    /**
     * Permet de modifier les dégâts reçus par cette carte de la part d'un attaquant.
     * @param originalDamage Les dégâts initiaux reçus.
     * @param attacker La carte attaquante.
     * @return Les dégâts modifiés.
     */
    public abstract int modifyDamageReceived(int originalDamage, Card attacker);

    /**
     * Permet de modifier la valeur d'attaque brute de la carte (ex: effet de Puant sur la carte adverse).
     * @param originalAttack La valeur d'attaque initiale.
     * @return La valeur d'attaque modifiée.
     */
    public abstract int modifyAttackValue(int originalAttack);
}
