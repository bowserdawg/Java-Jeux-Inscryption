package cards.types;

/**
 * Une classe qui indique que la carte peut voler.
 */
public class Volant implements Typable{

    private static final String CLASS_NAME = "Volant";

    public Volant()
    {

    }

    @Override
    public String getType() {
        return CLASS_NAME;
    }

    @Override
    public boolean isNotRegular() {
        return true;
    }

    @Override
    public String toString() {
        return CLASS_NAME;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof  Typable;
    }
}
