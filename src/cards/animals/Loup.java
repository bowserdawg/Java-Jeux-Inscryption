package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Loup extends AnimalCard {
    public Loup(){
        super(DonneFactory.loup());
    }

    @Override
    public String getName()
    {
        return "Loup";
    }
}
