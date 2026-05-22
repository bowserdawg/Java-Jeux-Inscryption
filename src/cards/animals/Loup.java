package cards.animals;

import java.util.HashMap;

public class Loup extends AnimalCard {
    public Loup(){
        super(3, 2, 2, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Loup";
    }
}
