package cards;

import cards.donnes.CardDonne;
import cards.interfaces.Nommable;
import cards.types.Typable;

import java.util.Optional;

public abstract class Card implements Nommable {
    //private int m_lifePoints;

    private CardDonne m_donnes;

    /**
     * Crée une nouvelle carte.
     * @param donne
     */
    public Card(CardDonne donne){
        m_donnes = donne;
    }

    /**
     * Return le pv de la carte.
     * @return Le pv en tant qu'entier.
     */
    public int getLife(){

        var life = m_donnes.getPv();

        if(life.isEmpty())
            throw new RuntimeException("Le champ \"Vie\" est vide pour cette carte\n");

        return life.get();
    }

    /**
     * Donne des dégâts et à la carte et fait baisser ses points de vies.
     * @param nbDamage Le nombre de dégâts que la carte a recu.
     * @return Le nouveau pv de la carte.
     */
    public int receiveDamage(int nbDamage)
    {
        Optional<Integer> currentPv = m_donnes.getPv();

        if(currentPv.isEmpty())
            throw new RuntimeException("Card.receiveDamage : essaie d'attaque carte avec aucun PV.\n");

        int newPv = currentPv.get() - nbDamage;
        m_donnes.setPv(newPv);

        return newPv;
    }

    /**
     * Donne le pv de la carte.
     * @return Le pv de la carte si c'est un champ valide.
     */
    public Optional<Integer> getPV()
    {
        return m_donnes.getPv();
    }

    /**
     * Donne le niveau d'attaque de la carte.
     * @return L'attaque si c'est un champ valide.
     */
    public Optional<Integer> getAttaque()
    {
        return m_donnes.getAttaque();
    }

    /**
     * Donne le type de la carte.
     * @return Un objet qui implémente "Typable".
     */
    public Typable getType()
    {
        Typable type = m_donnes.getType();

        if(type == null)
            throw new RuntimeException("Le type d'une ne devrait jamais etre null.");

        return type;
    }

    /**
     * Donne les goutes de sangs de la carte.
     * @return Les goute de sang s'ils existent.
     */
    public Optional<Integer> getBloodDrops()
    {
        return m_donnes.getBloodDrops();
    }

    /**
     * Donne les os de la carte.
     * @return Les os s'ils existent.
     */
    public Optional<Integer> getBones()
    {
        return m_donnes.getBones();
    }

    // Vien de Nommable
    @Override
    abstract public String getName();

    @Override
    public String toString() {
        return getName() + " : (" + m_donnes + ")";
    }
}
