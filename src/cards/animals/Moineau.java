package cards.animals;

import cards.interfaces.Flyable;

import java.util.HashMap;

public class Moineau extends AnimalCard implements Flyable {
    public Moineau(){
        super(1, 2, 1, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalVolantHashMap;
    }

    @Override
    public String getName()
    {
        return "Moineau";
    }
}
