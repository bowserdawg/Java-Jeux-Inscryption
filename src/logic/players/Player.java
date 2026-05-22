package logic.players;

import cards.Card;
import logic.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Player {
    private ArrayList<Card> m_hand = new ArrayList<>();
    private Board m_board;
    
    public Player(CardStack stack, Board board) {
        for (int i = 0; i < 4; i++) {
            m_hand.add(stack.drawCard());
        }
        m_board = board;
    }
    
    public boolean placeCard(int column, int indexCardInHand) {
        return m_board.placeCard(2, column, m_hand.get(indexCardInHand));
        
    }
    
    public ArrayList<Card> getHand(){
        return m_hand;
    }
    
    public int getNbCards(){
        return m_hand.size();
    }
}
