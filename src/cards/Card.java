package cards;

import cards.interfaces.Attacker;

public abstract class Card {
    private int m_lifePoints;
    private String m_nom;
    

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
    
    @Override
    public String toString() {
        return "Card : " + m_nom;
    }
}
