package cards.animals;

import java.util.HashMap;

public class Punaise extends AnimalCard{
    public Punaise(){
        super(1,2,0,2);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Punaise";
    }
}
