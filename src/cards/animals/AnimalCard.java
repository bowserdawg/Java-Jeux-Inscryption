package cards.animals;

import cards.Card;

public abstract class AnimalCard extends Card {
    private int m_attackDamage;
    private int m_bloodDrops;
    private int m_bones;

    public AnimalCard(int attackDamage, int lifePoints, int bloodDrops, int bones){
        super(lifePoints);
        m_bloodDrops = bloodDrops;
        m_bones = bones;
        m_attackDamage = attackDamage;
    }
}
