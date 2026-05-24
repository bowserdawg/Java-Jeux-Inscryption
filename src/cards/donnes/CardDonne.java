package cards.donnes;

import java.util.Optional;

/**
 * Contient toutes les données qu'une carte peut contenir.
 * Une donnée non disponible peut être accédé pour savoir qu'elle existe,
 * mais elle ne peut pas être modifiée.
 */
public class CardDonne {

    // Les données disponibles.

    int m_pv;
    int m_attaque;
    int m_bloodDrop;
    int m_bones;
    String m_type;


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
    private CardDonne(int pv, int attaque, String type, int bloodDrop, int bones)
    {
        m_pv = pv;
        m_attaque = attaque;
        m_type = type;
        m_bones = bones;
        m_bloodDrop = bloodDrop;
    }

    /**
     * Une carte qui peut avoir du pv et de l'attaque.
     *
     * @param pv Les points de vie de la carte.
     * @param attaque Les points d'attaque de la carte.
     * @param bloodDrop Les goutte de sang de la carte.
     * @param bones Les os de la carte.
     */
    private CardDonne(int pv, int attaque, int bloodDrop, int bones)
    {
        m_pv = pv;
        m_attaque = attaque;
        m_type = null;
        m_bones = bones;
        m_bloodDrop = bloodDrop;
    }

    /**
     * Une carte qui a seulement du PV.
     * @param pv Les points de vie de la carte.
     */
    private CardDonne(int pv)
    {
        m_pv = pv;
        m_attaque = NO_VALUE;
        m_type = null;
    }

    /**
     * Constructeur par défaut pour CardDonne.
     */
    private CardDonne()
    {
        m_pv = NO_VALUE;
        m_attaque = NO_VALUE;
        m_type = null;
        m_bloodDrop = NO_VALUE;
        m_bones = NO_VALUE;
    }

    /**
     * Constructeur de copie pour CardDonne.
     * @param other La CardDonne copié.
     */
    public CardDonne(CardDonne other)
    {
        m_pv = other.m_pv;
        m_attaque = other.m_attaque;
        m_type = other.m_type;
        m_bloodDrop = other.m_bloodDrop;
        m_bones = other.m_bones;
    }

    /**
     *  Un ensemble de donnée avec aucune donnée.
     *
     * @return Une nouvelle CardDonne.
      */
    public static CardDonne withNothing()  { return new CardDonne(); }

    /**
     * Un ensemble de donnée avec seulement du pv.
     * @param pv Le pv.
     * @return Une nouvelle CardDonne avec les paramètres spécifiée.
     */
    public static CardDonne withPv(int pv) { return new CardDonne(pv); }

    // Un ensemble de donnée avec du pv et de l'attaque.

    /**
     * Un ensemble de donnée avec du pv et de l'attaque.
     * @param pv Le pv.
     * @param attaque L'attaque.
     * @param gouteSang Goute de sang.
     * @param os L'Os.
     * @return Une nouvelle CardDonne avec les paramètres spécifiée.
     */
    public static CardDonne withPvAttBloodAndBone(int pv, int attaque, int gouteSang, int os)
    {
        return new CardDonne(pv, attaque, gouteSang, os);
    }

    /**
     * Un ensemble de donnée avec du pv de l'attaque et un type.
     * @param pv Le pv.
     * @param attaque L'attaque.
     * @param type Le type de la carte (Exemple : volant)
     * @param gouteSang Goute de sang.
     * @param os L'Os.
     * @return ne nouvelle CardDonne avec les paramètres spécifiés.
     */
    public static CardDonne withPvAttTypeBloodAndBone(int pv, int attaque, String type, int gouteSang, int os)
    {
        return new CardDonne(pv, attaque, type, gouteSang, os);
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
        m_pv = num;
    }

    /**
     * Donne le pv actuel.
     *
     * @return Un Optional qui peut contenir le pv.
     */
    public Optional<Integer> getPv()
    {
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
        m_attaque = num;
    }

    /**
     * Donne la valeur d'attaque actuelle des cartes.
     *
     * @return Un Optional qui peut contenir la valeur d'attaque.
     */
    public Optional<Integer> getAttaque()
    {
        return Optional.of(m_attaque);
    }


    /**
     * Change le type.
     *
     * @param newType Le nouveau type.
     */
    public void setType(String newType)
    {
        if(m_type == null)
            throw new RuntimeException("Le champ \"Type\" ne peut etre modifie pour ce type de donne");
        m_type = newType;
    }

    /**
     * Donne le type.
     *
     * @return Le type.
     */
    public Optional<String> getType()
    {
        return Optional.of(m_type);
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
        m_bloodDrop = bloodDrop;
    }

    /**
     * Donne le goute de sang.
     *
     * @return Le goute de sang s'il existe.
     */
    public Optional<Integer> getBloodDrop()
    {
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
        return Optional.of(m_bones);
    }
}
