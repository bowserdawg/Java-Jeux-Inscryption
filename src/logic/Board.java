package logic;

import cards.Card;
import cards.animals.AnimalCard;

public class Board {

    private  int m_nbPartie;
    private int m_nbTour;
    private final int m_width = 4;
    private final int m_height = 3;
    private int m_playerScore = 0;
    private int m_userBones = 0;
    private int m_rivalBones = 0;


    public enum PositionsRow {
        NextRivalRow,
        RivalRow,
        UserRow
    }

    private final Card[][] m_board = new Card[m_height][m_width];
    
    public Board() {
        m_nbPartie = 1;
        m_nbTour = -1;
    }
    
    public Board(int nbPartie) {
        m_nbPartie = nbPartie;
        m_nbTour = -1;
        
    }

    /**
     *
     * @return Le score de la partie.
     */

    public int getScore() {
        return m_playerScore;
    }

    /**
     *
     * @return Le nombre de parties
     */

    public int getNbPartie() {
        return m_nbPartie;
    }

    /**
     * @return Le Nombre de tours
     *
     */
    public int getNbTour() {
        return m_nbTour;
    }

    /**
     * Renvoie la carte à la case indiquée
     *
     * @param row    La ligne de la case
     * @param column La colonne de la case
     * @return Une carte s'il y en a une. Null si aucune carte n'est présente à la case indiquée
     */
    public Card getCard(PositionsRow row, int column) {
        return m_board[row.ordinal()][column];
    }

    /**
     * On admet que c'est le jureur qui commence au tour 0, 0 qui est pair.
     * Par concevant quand le tour est pair, c'est le tour du joueur
     *
     * @return true quand c'est le nombre de tours est pair
     */

    public boolean isPlayerTurn() {
        return m_nbTour % 2 == 0;
    }

    /**
     * Place une carte à la position indiquée
     *
     * @param row         La ligne où placer la carte
     * @param column      La colonne où placer la carte
     * @param cardToPlace La carte à placer
     * @return Vrai si la carte à était placé.
     */
    public boolean placeCard(PositionsRow row, int column, Card cardToPlace) {
        if (isOccupied(row, column)) {
            return false;
        }
        m_board[row.ordinal()][column] = cardToPlace;
        return true;
    }


    /**
     * Attaque les cartes entre elles.
     * Applique, aux cartes en amont de la ligne de l'adversaire, les dégats infligé par excès aux cartes devant.
     * Avance les prochaines cartes placées par l'adversaire
     * Incremente le nombre de tours de la partie.
     *
     * @return Le nombre de cartes qui on était tué
     */
    public void passTurn() {
        m_nbTour++;

        // Déclencher le début du tour pour le joueur dont c'est le tour (ex: Croissance)
        PositionsRow activeRow = isPlayerTurn() ? PositionsRow.UserRow : PositionsRow.RivalRow;
        for (int i = 0; i < getWidth(); i++) {
            Card card = getCard(activeRow, i);
            if (card != null) {
                var pouvoirsCopy = new java.util.ArrayList<>(card.getPouvoirs());
                for (var p : pouvoirsCopy) {
                    p.onTurnStart(this, card, activeRow.ordinal(), i);
                }
            }
        }

        Card[] rivalRow = m_board[1];
        Card[] playerRow = m_board[2];
        Card[] nextRivalRow = m_board[0];

        for (int i = 0; i < rivalRow.length; i++) {
            Card rivalCard = rivalRow[i];
            Card playerCard = playerRow[i];

            Card attacker;
            Card victim;

            if (isPlayerTurn()) {
                attacker = playerCard;
                victim = rivalCard;
            } else {
                attacker = rivalCard;
                victim = playerCard;
            }

            // Volant : attaque directe du score (victim = null)
            if (attacker != null && attacker.getType().getType().equals("Volant")) {
                victim = null;
            }

            if (attacker != null && attacker.getAttaque().isPresent()) {
                ((AnimalCard) attacker).attack(this, victim);
            }
            checkCardLife();
        }

        // Actions de fin d'attaque (ex: Coureur)
        java.util.Set<Card> processedCards = new java.util.HashSet<>();
        for (int i = 0; i < rivalRow.length; i++) {
            Card card = isPlayerTurn() ? playerRow[i] : rivalRow[i];
            if (card != null && !processedCards.contains(card)) {
                processedCards.add(card);
                PositionsRow pRow = isPlayerTurn() ? PositionsRow.UserRow : PositionsRow.RivalRow;
                int currentCol = -1;
                for (int c = 0; c < getWidth(); c++) {
                    if (getCard(pRow, c) == card) {
                        currentCol = c;
                        break;
                    }
                }
                if (currentCol != -1) {
                    var pouvoirsCopy = new java.util.ArrayList<>(card.getPouvoirs());
                    for (var p : pouvoirsCopy) {
                        p.onAfterAttack(this, card, pRow.ordinal(), currentCol);
                    }
                }
            }
        }

        if (!isPlayerTurn()) {
            for (int i = 0; i < nextRivalRow.length; i++) {
                if (nextRivalRow[i] != null && rivalRow[i] == null) {
                    rivalRow[i] = nextRivalRow[i];
                    nextRivalRow[i] = null;
                }
            }
        }}

    public void checkScore(){

    }

    /**
     *
     * @return La longueur du plateau
     */

    public int getWidth() {
        return m_width;
    }

    public void checkCardLife() {
        for (PositionsRow row : PositionsRow.values()) {
            for (int column = 0; column < getWidth(); column++) {
                Card card = getCard(row, column);
                if (card != null) {
                    if (card.getLife() <= 0) {
                        removeCard(row, column);
                    }
                }
            }
        }
    }

    /**
     *
     * @param row    La ligne de la case dont on veut connaitre l'occupation
     * @param column La colonne de la case dont on veut connaitre l'occupation
     * @return Vrai si la case sélectionnée est occupé
     */
    public boolean isOccupied(PositionsRow row, int column) {
        return m_board[row.ordinal()][column] != null;
    }

    /**
     *
     * @return La largeur du plateau
     */

    public int getHeight() {
        return m_height;
    }

    /**
     *
     * S'occupe de mettre à jour le score selon les attaques fait les uns aux autres
     *
     * @param damage : Les dégâts à infliger à l'adversaire
     */

    public void attack(int damage) {
        if (isPlayerTurn()) {
            m_playerScore += damage;
        } else {
            m_playerScore -= damage;
        }
    }

    public void removeCard(PositionsRow row, int column) {
        m_board[row.ordinal()][column] = null;
        if (row == PositionsRow.UserRow) {
            m_userBones++;
        } else if (row == PositionsRow.RivalRow || row == PositionsRow.NextRivalRow) {
            m_rivalBones++;
        }
    }

    public boolean isCardSacrifiable(PositionsRow row, int column) {
        Card card = getCard(row, column);
        if (card != null) {
            boolean sacrificeHandled = false;
            for (var p : card.getPouvoirs()) {
                if (p.onSacrifice(this, card, row.ordinal(), column)) {
                    sacrificeHandled = true;
                }
            }
            if (sacrificeHandled) {
                if (row == PositionsRow.UserRow) {
                    m_userBones++;
                } else if (row == PositionsRow.RivalRow || row == PositionsRow.NextRivalRow) {
                    m_rivalBones++;
                }
            }
            
            return true;
        } else return false;
    }

    public int getNbSacrifiableCards(PositionsRow row) {
        int nbSacrifiableCards = 0;
        for (int i = 0; i < getWidth(); i++) {

            if (getCard(PositionsRow.UserRow, i) != null) {
                if (getCard(PositionsRow.UserRow, i).isSacrifiable()) {
                    nbSacrifiableCards++;
                }
            }
        }
        return nbSacrifiableCards;
    }

    public int getRemainingBones(BoneProperties boneProperties) {
        if (boneProperties == BoneProperties.USER) {
            return m_userBones;
        } else  {
            return m_rivalBones;
        }
    }

    public void increasePartie(){
        m_nbPartie++;
    }
}
