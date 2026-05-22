package cards.animals;

import java.util.HashMap;

public class Louveteau extends AnimalCard {
    public Louveteau(){
        super(0, 1, 1, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Louveteau";
    }
}
