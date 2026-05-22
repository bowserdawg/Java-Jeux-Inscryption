package cards.animals;

import java.util.HashMap;

public class Coyote extends AnimalCard{
    public Coyote(){
        super(2, 1, 0, 4);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Coyotte";
    }
}
