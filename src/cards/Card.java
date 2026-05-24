package cards;

import cards.donnes.CardDonne;
import cards.interfaces.Nommable;

import java.util.HashMap;
import java.util.Optional;

public abstract class Card implements Nommable {
    private int m_lifePoints;

    private CardDonne m_donnes;

    public Card(int lifePoints, CardDonne donne){
        m_lifePoints = lifePoints;
        m_donnes = donne;
    }
    
    public int recivedDamage(int nbDamage){
         m_lifePoints = m_lifePoints - nbDamage;
         return m_lifePoints;
    }
    
    public int getLife(){
        return m_lifePoints;
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

    public Optional<String> getType()
    {
        return m_donnes.getType();
    }

    public static String[] DONNE_TYPE = {"PV", "ATT", "SPECIAL"};
    public enum DONNE_TYPE_ELEMS
    {
        PV,
        ATTAQUE,
        TYPE_SPECIAL,
    }
    public static HashMap<String, String> s_defaultHashMap;
    static {
        s_defaultHashMap = new HashMap<>();
        s_defaultHashMap.put(DONNE_TYPE[DONNE_TYPE_ELEMS.PV.ordinal()], "PV: ");
    }

    /**
     * Cette fonction retourne les données que la classe contient.
     * Des classes peuvent contenir des données que d'autres classes n'ont pas.
     *
     * Consulter DONNE_TYPE pour voire les clés primaires possibles.
     *
     * @return Une hashmap avec les données disponibles des classes.
     * L'Hashmap devrait être statique.
     */
    abstract public HashMap<String, String> getData();

    // Vien de Nommable
    @Override
    abstract public String getName();
}
