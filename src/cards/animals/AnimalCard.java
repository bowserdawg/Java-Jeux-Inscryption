package cards.animals;

public abstract class AnimalCard {
    private int m_bloodDrops;
    private int m_bones;
    private double m_attackDamage;
    private int m_lifePoints;
    
    public AnimalCard(double attackDamage, int lifePoints, int bloodDrops, int bones){
        m_bloodDrops = bloodDrops;
        m_bones = bones;
        m_attackDamage = attackDamage;
        m_lifePoints = lifePoints;
    }
}
