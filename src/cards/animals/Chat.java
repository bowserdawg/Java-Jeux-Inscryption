package cards.animals;

import java.util.HashMap;

public class Chat extends AnimalCard{
    
    public Chat(){
        super(0, 1, 1, 0);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_animalDefaultHashMap;
    }

    @Override
    public String getName()
    {
        return "Chat";
    }
}
