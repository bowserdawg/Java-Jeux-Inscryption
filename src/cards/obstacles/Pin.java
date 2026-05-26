package cards.obstacles;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Pin extends ObstacleCard{
    public Pin(){
        super(DonneFactory.sapin());
    }


    @Override
    public String getName() {
        return "Pin";
    }
}
