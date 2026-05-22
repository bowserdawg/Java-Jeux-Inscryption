package cards.animals;

import java.util.HashMap;

public class Grizzly extends AnimalCard {
    public Grizzly(){
        super(4, 6, 3, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Grizzly";
    }
}
