package cards.donnes;

import cards.types.Regular;
import cards.types.Typable;

import java.util.Optional;

/**
 * Contient toutes les données qu'une carte peut contenir.
 * Une donnée non disponible peut être accédé pour savoir qu'elle existe,
 * mais elle ne peut pas être modifiée.
 * La variable membre "m_type" ne peut jamais être null et doit être Regular() à la place.
 */
public class CardDonne {

    // Les données disponibles.

    int m_pv;
    int m_attaque;
    int m_bloodDrop;
    int m_bones;
    Typable m_type;


    // Si l'un des valeurs n'est jamais utilisé.
    private static final int NO_VALUE = -1;

    /**
     * Une carte qui peut avoir du pv, de l'attaque et un type.
     *
     * @param pv Les points de vie de la carte.
     * @param attaque  Les points d'attaque de la carte.
     * @param type Le type de la carte (ex: Volant)
     * @param bloodDrop Les goutte de sang de la carte.
     * @param bones Les os de la carte.
     */
    protected CardDonne(int pv, int attaque, int bloodDrop, int bones, Typable type)
    {
        m_pv = pv;
        m_attaque = attaque;
        m_type = type;
        m_bones = bones;
        m_bloodDrop = bloodDrop;
    }

    /**
     * Une carte qui a seulement du PV.
     * @param pv Les points de vie de la carte.
     */
    protected CardDonne(int pv)
    {
        m_pv = pv;
        m_attaque = NO_VALUE;
        m_type = new Regular();
        m_bones = NO_VALUE;
        m_bloodDrop = NO_VALUE;
    }

    /**
     * Constructeur par défaut pour CardDonne.
     * A ne pas utiliser.
     */
    protected CardDonne()
    {
        m_pv = NO_VALUE;
        m_attaque = NO_VALUE;
        m_bloodDrop = NO_VALUE;
        m_bones = NO_VALUE;
        m_type = new Regular();
    }

    /**
     * Constructeur de copie pour CardDonne.
     * @param other La CardDonne copié.
     */
    protected CardDonne(CardDonne other)
    {
        m_pv = other.m_pv;
        m_attaque = other.m_attaque;
        m_type = other.m_type;
        m_bloodDrop = other.m_bloodDrop;
        m_bones = other.m_bones;
    }

    /**
     * Change le pv.
     *
     * @param num Le nouveau pv.
      */
    public void setPv(int num)
    {
        if(m_pv == NO_VALUE)
            throw new RuntimeException("Le champ \"PV\" ne peut être modifie pour ce type de donne");
        m_pv = Math.max(num, 0);
    }

    /**
     * Donne le pv actuel.
     *
     * @return Un Optional qui peut contenir le pv.
     */
    public Optional<Integer> getPv()
    {
        if(m_pv == NO_VALUE)
            return Optional.empty();
        return Optional.of(m_pv);
    }


    /**
     * Change l'attaque.
     *
     * @param num La nouvelle valeur de l'attaque.
     */
    public void setAttaque(int num)
    {
        if(m_attaque == NO_VALUE)
            throw new RuntimeException("Le champ \"Attaque\" ne peut être modifie pour ce type de donne");
        // La valeur ne doit pas etre negative.
        m_attaque = Math.max(num, 0);
    }

    /**
     * Donne la valeur d'attaque actuelle des cartes.
     *
     * @return Un Optional qui peut contenir la valeur d'attaque.
     */
    public Optional<Integer> getAttaque()
    {
        if(m_attaque == NO_VALUE)
            return Optional.empty();
        return Optional.of(m_attaque);
    }


    /**
     * Change le type.
     *
     * @param newType Le nouveau type.
     */
    public void setType(Typable newType)
    {
        if(m_type == null)
            throw new NullPointerException("Le champ \"Type\" ne peut être null");
        m_type = newType;
    }

    /**
     * Donne le type.
     *
     * @return Le type.
     */
    public Typable getType()
    {
        return m_type;
    }

    /**
     * Change le niveau de goute de sang.
     *
     * @param bloodDrop Le nouveau niveau de goute de sang.
     */
    public void setBloodDrop(int bloodDrop)
    {
        if(m_bloodDrop == NO_VALUE)
            throw new RuntimeException("Le champ \"Gouttes de sang\" ne peut être modifie pour ce type de donne");
        // La valeur ne doit pas etre negative.
        m_bloodDrop = Math.max(bloodDrop, 0);
    }

    /**
     * Donne le goute de sang.
     *
     * @return Le goute de sang s'il existe.
     */
    public Optional<Integer> getBloodDrops()
    {
        if(m_bloodDrop == NO_VALUE)
            return Optional.empty();
        return Optional.of(m_bloodDrop);
    }

    /**
     * Change la valeur d'os.
     *
     * @param bones La nouvelle valeur d'os.
     */
    public void setBones(int bones)
    {
        if(m_bones == NO_VALUE)
            throw new RuntimeException("Le champ \"Os\" ne peut être modifie pour ce type de donne");
        m_bones = bones;
    }

    /**
     * Donne le goute de sang.
     *
     * @return La valeur d'os si elle existe.
     */
    public Optional<Integer> getBones()
    {
        if(m_bones == NO_VALUE)
            return Optional.empty();
        return Optional.of(m_bones);
    }

    @Override
    public String toString() {
        return "Donne (PV: " + m_pv
                + ", ATT: "  + m_attaque
                + ", Type: " + m_type
                + ", Goute de Sang: " + m_bloodDrop
                + ", Os: " + m_bones + ")";
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof CardDonne other))
        {
            return false;
        }

        return ( (m_bones == other.m_bones) && (m_type.equals(other.m_type))
                && (m_pv == other.m_pv) && (m_attaque == other.m_attaque)
                && (m_bloodDrop == other.m_bloodDrop) );
    }
}
