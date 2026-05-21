package cards.obstacles;

import cards.Card;

import java.util.HashMap;

public class Rock extends ObstacleCard{
    public Rock(){
        super(5);
    }

    @Override
    public String getName() {
        return "Rock";
    }

    @Override
    public HashMap<String, String> getData() {
        return s_defaultHashMap;
    }
}
