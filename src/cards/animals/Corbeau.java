package cards.animals;

import cards.interfaces.Flyable;

import java.util.HashMap;

public class Corbeau  extends AnimalCard implements Flyable {
    public Corbeau(){
        super(2, 3, 2, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalVolantHashMap;
    }

    @Override
    public String getName()
    {
        return "Corbeau";
    }
}
