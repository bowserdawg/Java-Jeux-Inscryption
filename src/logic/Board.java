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
    private boolean m_playerTurn = true;
    private final int m_width = 4;
    private final int m_height = 3;
    private int m_playerScore = 0;

    private Card[][] m_board = new Card[m_height][m_width];
    
    public Board() {
        m_nbPartie = 1;
        m_nbTour = 1;
    }

    public int getScore()
    {
        return m_playerScore;
    }

    public int getNbPartie() {
        return m_nbPartie;
    }
    
    public int getNbTour() {
        return m_nbTour;
    }
    
    // Peut retourner null
    public Card getCard(int row, int column) {
        return m_board[row][column];
    }

    
    public boolean placeCard(int row, int column, Card cardToPlace) {
        if (isOccupied(row, column)) {
            return false;
        }
        m_board[row][column] = cardToPlace;
        return true;
    }
    
    public void passTurn() {
        Card[] rivalRow = m_board[1];
        Card[] playerRow = m_board[2];
        Card[] nextRivalRow = m_board[0];
        for (int i = 0; i < rivalRow.length; i++) {
            Card rivalCard = rivalRow[i];
            Card playerCard = playerRow[i];

            Card attacker;
            Card victim;

            if (m_playerTurn) {
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
                    if (m_playerTurn) {
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


//            if (m_playerTurn) {
//
//                if (playerCard == null) {
//                    continue;
//                }
//
//                Optional<Integer> tempAttack = playerCard.getAttaque();
//                if (tempAttack.isPresent()) {
//
//                    if (rivalCard == null) {
//                        int attack = tempAttack.get();
//                        attackRival(attack);
//                    } else {
//                        Attacker playerAttack = (AnimalCard) playerCard;
//                        playerAttack.attack(rivalCard);
//                    }
//                }
//            } else {
//                if (rivalCard == null) {
//                    continue;
//                }
//
//                Optional<Integer> tempAttack = rivalCard.getAttaque();
//                if (tempAttack.isPresent()) {
//
//                    if (playerCard == null) {
//                        int attack = tempAttack.get();
//                        attackPlayer(attack);
//                    } else {
//                        Attacker rivalAttack = (AnimalCard) playerCard;
//                        rivalAttack.attack(rivalCard);
//                    }
//                }
//            }
//        }
    }


    public int getWidth() {
        return m_width;
    }
    
    public boolean isOccupied(int row, int column) {
        return m_board[row][column] != null;
    }
    
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
        if (m_playerTurn) {
            m_playerScore += damage;
        } else {
            m_playerScore -= damage;
        }
    }
}
