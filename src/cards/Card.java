package cards;

import cards.interfaces.Attacker;

public abstract class Card {
    private int m_lifePoints;

    public Card(int lifePoints){
        m_lifePoints = lifePoints;
    }
    
    public int recivedDamage(int nbDamage){
         m_lifePoints = m_lifePoints - nbDamage;
         return m_lifePoints;
    }
    
    public int getLife(){
        return m_lifePoints;
    }
}
