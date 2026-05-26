package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Grizzly extends AnimalCard {
    public Grizzly(){
        super(DonneFactory.grizzly());
    }


    @Override
    public String getName()
    {
        return "Grizzly";
    }
}
