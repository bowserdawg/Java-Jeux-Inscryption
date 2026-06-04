package logic;

import cards.Card;
import cards.animals.AnimalCard;
import cards.animals.Chat;
import cards.interfaces.Attacker;
import display.Display;

import java.util.Optional;

public class Board {
    
    private int m_nbPartie;
    private int m_nbTour;
    private final int m_width = 4;
    private final int m_height = 3;
    private int m_playerScore = 0;
    
    public enum Positions {
        NextRivalRow,
        RivalRow,
        UserRow
    }
    
    private Card[][] m_board = new Card[m_height][m_width];
    
    public Board() {
        m_nbPartie = 1;
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
     * @param row La ligne de la case
     * @param column La colonne de la case
     * @return Une carte s'il y en a une. Null si aucune carte n'est présente à la case indiqué
     */
    public Card getCard(Positions row, int column) {
        return m_board[row.ordinal()][column];
    }
    
    /**
     * On admet que c'est le joureur qui commence au tour 0, 0 qui est pair.
     * Par concequant quand le tour est pair s'est le tour du joueur
     *
     * @return true quand c'est le nombre de tour est pair
     */
    
    public boolean isPlayerTurn() {
        return m_nbTour % 2 == 0;
    }
    
    /**
     * Place une carte à la position indiqué
     * @param row La ligne où placer la carte
     * @param column La colonne où placer la carte
     * @param cardToPlace La carte à placer
     * @return Vrai si la carte à était placé.
     */
    public boolean placeCard(Positions row, int column, Card cardToPlace) {
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
     */
    public void passTurn() {
        Card[] rivalRow = m_board[1];
        Card[] playerRow = m_board[2];
        Card[] nextRivalRow = m_board[0];
        m_nbTour++;
        
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
            
            if (attacker != null && attacker.getAttaque().isPresent()) {
                ((AnimalCard) attacker).attack(this, victim);
            }
            
            Card nextRivalCard = nextRivalRow[i];
            if (victim != null) {
                
                int cardLife = victim.getLife();
                
                if (cardLife <= 0) {
                    if (isPlayerTurn()) {
                        if (cardLife < 0) {
                            if (nextRivalCard.receiveDamage(cardLife) <= 0) {
                                nextRivalRow[i] = null;
                            }
                        }
                        playerRow[i] = null;
                    } else {
                        rivalRow[i] = null;
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
        }
        
    }
    
    /**
     *
     * @return La longueur du plateau
     */
    
    public int getWidth() {
        return m_width;
    }
    
    
    /**
     *
     * @param row    La ligne de la case dont on veut connaitre l'occupation
     * @param column La colonne de la case dont on veut connaitre l'occupation
     * @return Vrai si la case séléctionné est occupé
     */
    public boolean isOccupied(Positions row, int column) {
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
     * @param damage : Les dégats à infliger à l'adversaire
     */
    
    public void attack(int damage) {
        if (isPlayerTurn()) {
            m_playerScore += damage;
        } else {
            m_playerScore -= damage;
        }
    }
}
