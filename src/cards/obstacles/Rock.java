package cards.obstacles;

import cards.Card;
import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Rock extends ObstacleCard{
    public Rock(){
        super(DonneFactory.rocher());
    }

    @Override
    public String getName() {
        return "Rock";
    }
}
