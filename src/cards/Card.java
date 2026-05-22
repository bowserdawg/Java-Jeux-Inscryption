package cards;

import cards.interfaces.Attacker;
import cards.interfaces.Nommable;
import cards.obstacles.ObstacleCard;

import java.util.HashMap;

public abstract class Card implements Nommable {
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
