package cards.animals;

import cards.Card;
//import cards.interfaces.Attacker;
import cards.interfaces.*;

public abstract class AnimalCard extends Card implements Attacker {
    private int m_attackDamage;
    private int m_bloodDrops;
    private int m_bones;
    private TypeCost m_costType;
    
    public enum TypeCost {
        BloodDrops,
        Bones;
    }
    
    public AnimalCard(int attackDamage, int lifePoints, int bloodDrops, int bones) {
        super(lifePoints);
        m_attackDamage = attackDamage;
        
        if (bloodDrops == 0) {
            m_costType = TypeCost.Bones;
            m_bones = bones;
        } else {
            m_bloodDrops = bloodDrops;
            m_costType = TypeCost.BloodDrops;
        }
    }
    
    public int getCost() {
        if (getTypeCost() == TypeCost.Bones) {
            return m_bones;
        }
        return m_bloodDrops;
    }
    
    public TypeCost getTypeCost() {
        
        return m_costType;
    }
    
    @Override
    public void attack(Card card) {
        card.recivedDamage(m_attackDamage);
    }
}

