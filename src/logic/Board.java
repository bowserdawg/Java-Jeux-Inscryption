package logic;

import cards.Card;
import cards.animals.Chat;
import display.Display;

public class Board {
    
    private Display m_display;
    private int m_nbPartie;
    private int m_nbTour;
    private final int m_width = 4;
    private final int m_height = 3;
    
    private Card[][] m_board = new Card[m_height][m_width];
    
    public Board() {
        m_display = new Display();
        m_nbPartie = 1;
        m_nbTour = 1;
    }
    
    public int getM_nbPartie() {
        return m_nbPartie;
    }
    
    public int getM_nbTour() {
        return m_nbTour;
    }
    
    // Peut retourner null
    public Card getCard(int row, int column) {
        return m_board[row][column];
    }
    
    public void display() {
        //m_display.draw(this);
    }
    
    public void setCards() {
        m_board[0][0] = new Chat();
        m_board[0][1] = new Chat();
        m_board[0][2] = new Chat();
        m_board[0][3] = new Chat();
        
        m_board[1][0] = null;
        m_board[1][1] = null;
        m_board[1][2] = null;
        m_board[1][3] = null;
        
        m_board[2][0] = new Chat();
        m_board[2][1] = new Chat();
        m_board[2][2] = new Chat();
        m_board[2][3] = new Chat();
    }
    
    public boolean placeCard(int row, int column, Card cardToPlace) {
        if (isOccupied(row, column)) {
        return false;
        }
            m_board[row][column] = cardToPlace;
            return true;
    }
    
    public void passTurn() {
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
