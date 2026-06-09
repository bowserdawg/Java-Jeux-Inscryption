package logic.players;

import cards.animals.AnimalCard;
import cards.animals.Ecureuil;
import logic.Board;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Player {
    private ArrayList<AnimalCard> m_hand = new ArrayList<>();
    private Board m_board;
    private CardStack m_stack;
    
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
        m_stack = stack;
    }
    
    public abstract boolean placeCard(int column, int indexCardInHand);
    
    public Board getBoard() {
        return m_board;
    }
    
    public ArrayList<AnimalCard> getHand() {
        return m_hand;
    }
    
    public int getNbCards() {
        return m_hand.size();
    }
    
    public Optional<AnimalCard> getCardFromHand(int index) {
        if (index  > m_hand.size() - 1 ) {
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
    


    
    public boolean drawCard() {
        Optional<AnimalCard> op = m_stack.drawCard();
        
        if (op.isEmpty()) {
            return false;
        }
        
        m_hand.add(op.get());
        
        return true;
    }
    
    public boolean drawSquirrel() {
        Optional<Ecureuil> tempCard = m_stack.drawSquirrel();
        if (tempCard.isPresent()) {
            Ecureuil squirrel = tempCard.get();
            m_hand.add(squirrel);
            return true;
        }
        return false;
    }
    
    /**
     * Nombre d'écureuils restants.
     *
     * @return Un entier.
     */
    public int remainingSquirrels() {
        return m_stack.getSquirrels();
    }
    
    /**
     * Nombre de cartes restant.
     *
     * @return Un entier.
     */
    public int remainingCards() {
        return m_stack.remainingCards();
    }
    
    
}
