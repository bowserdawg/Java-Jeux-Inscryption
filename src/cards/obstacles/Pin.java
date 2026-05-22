package cards.obstacles;

import java.util.HashMap;

public class Pin extends ObstacleCard{
    public Pin(){
        super(3);
    }

    @Override
    public HashMap<String, String> getData() {
        return s_defaultHashMap;
    }

    @Override
    public String getName() {
        return "Pin";
    }
}
