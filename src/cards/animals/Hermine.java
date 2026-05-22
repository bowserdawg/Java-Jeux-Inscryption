package cards.animals;

import java.util.HashMap;

public class Hermine extends AnimalCard {
    public Hermine(){
        super(1, 3, 1, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Hermine";
    }
}
