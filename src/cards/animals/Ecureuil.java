package cards.animals;

import java.util.HashMap;

public class Ecureuil extends AnimalCard{
    public Ecureuil(){
        super(0, 1, 0, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Ecureuil";
    }
}
