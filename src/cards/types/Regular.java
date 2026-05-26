package cards.types;

/**
 * Une classe qui indique qu'il n'y a pas variment de type.
 */
public class Regular implements Typable{

    private static final String CLASS_NAME = "Regular";

    public Regular()
    {

    }

    @Override
    public String getType() {
        return CLASS_NAME;
    }

    @Override
    public boolean isNotRegular() {
        return false;
    }

    @Override
    public String toString() {
        return CLASS_NAME;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Regular;
    }
}
