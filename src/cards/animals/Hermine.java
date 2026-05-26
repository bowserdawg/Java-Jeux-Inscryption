package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Hermine extends AnimalCard {
    public Hermine(){
        super(DonneFactory.hermine());
    }

    @Override
    public String getName()
    {
        return "Hermine";
    }
}
