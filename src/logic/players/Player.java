package logic.players;

import cards.Card;
import cards.animals.AnimalCard;
import logic.Board;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public abstract class Player {
    private ArrayList<AnimalCard> m_hand = new ArrayList<>();
    private Board m_board;
    
    public Player(CardStack stack, Board board) {
        for (int i = 0; i < 4; i++) {
            Optional<AnimalCard> tempCard = stack.drawCard();
            if (tempCard.isPresent()) {
                m_hand.add(tempCard.get());
            } else {
                break;
            }
        }
        m_board = board;
    }
    
    public boolean placeCard(int column, int indexCardInHand) {
        if (!m_hand.isEmpty()) {
            Optional<AnimalCard> tempCard = getCardFromHand(indexCardInHand);
            AnimalCard selectedCard;
            if (tempCard.isPresent()) {
                selectedCard = tempCard.get();
                if (m_board.placeCard(2, column, selectedCard)) {
                    return removeCardFromHand(selectedCard);
                }
            }
            return false;
            
        } else {
            return false;
        }
    }
    
    public ArrayList<AnimalCard> getHand() {
        return m_hand;
    }
    
    public int getNbCards() {
        return m_hand.size();
    }
    
    public Optional<AnimalCard> getCardFromHand(int index) {
        if (m_hand.size() < index + 1) {
            return Optional.empty();
        }
        AnimalCard selectedCard = m_hand.get(index);
        return Optional.of(selectedCard);
    }
    
    public boolean removeCardFromHand(AnimalCard card) {
        if (!m_hand.contains(card)) {
            return false;
        }
        m_hand.remove(card);
        return true;
    }
}
