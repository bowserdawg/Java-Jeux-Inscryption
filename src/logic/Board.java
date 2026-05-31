package logic;

import cards.Card;
import cards.animals.Chat;
import display.Display;

public class Board {
    
    private int m_nbPartie;
    private int m_nbTour;
    private boolean playerTurn = true;
    private final int m_width = 4;
    private final int m_height = 3;
    
    private Card[][] m_board = new Card[m_height][m_width];
    
    public Board() {
        m_nbPartie = 1;
        m_nbTour = 1;
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
        for(int i = 0; i < m_width; i++){
            for(int j = 0; j< m_height; j++){
                
                getCard(i,j);
            }
        }
    }
    
    public int getWidth(){
        return m_width;
    }
    
    public boolean isOccupied(int row, int column){
        return m_board[row][column] != null;
    }
    
    public int getHeight(){
        return m_height;
    }
}
