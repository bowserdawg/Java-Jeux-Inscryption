package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Ecureuil extends AnimalCard{
    public Ecureuil(){
        super(DonneFactory.ecureuil());
    }

    @Override
    public String getName()
    {
        return "Ecureuil";
    }
}
