package logic.players;

import cards.animals.AnimalCard;
import cards.animals.Ecureuil;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class CardStack {
    static final Random r = new Random();
    ArrayList<AnimalCard> m_cards;
    //    ArrayList<Ecureuil> m_squirrels = new ArrayList<>();
    int m_squirrels = 10;
    
    public CardStack(ArrayList<AnimalCard> cardList) {
        m_cards = cardList;
    }
    
    public Optional<AnimalCard> drawCard() {
        if (!m_cards.isEmpty()) {
            
            int index = r.nextInt(0, m_cards.size());
            AnimalCard cardReturned = m_cards.get(index);
            m_cards.remove(index);
            return Optional.of(cardReturned);
        }
        return Optional.empty();
    }
    
    public Optional<Ecureuil> drawSquirrel() {
        if (m_squirrels > 0) {
            m_squirrels--;
            return Optional.of(new Ecureuil());
        }
        return Optional.empty();
    }
    
    public ArrayList<AnimalCard> getCards() {
        return m_cards;
    }
    
    public int getSquirrels() {
        return m_squirrels;
    }
}
