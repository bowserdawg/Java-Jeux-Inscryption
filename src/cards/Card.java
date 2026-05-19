package cards;

import cards.interfaces.Attacker;

public abstract class Card {
    private int m_lifePoints;

    public Card(int lifePoints){
        m_lifePoints = lifePoints;
    }
}
