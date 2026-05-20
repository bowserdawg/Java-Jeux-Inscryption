package logic.players;

import cards.Card;
import logic.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Player {
    private ArrayList<Card> m_hand;
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
    
    public Card[] getHand(){
        if(m_hand.isEmpty()){
            return new Card[0];
        }else {
        return (Card[])m_hand.toArray();
        }
    }
}
