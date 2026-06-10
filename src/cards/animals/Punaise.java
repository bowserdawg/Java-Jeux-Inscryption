package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Punaise extends AnimalCard{
    public Punaise(){
        super(DonneFactory.punaise());
        addPouvoir(new cards.pouvoir.Puant());
    }

    @Override
    public String getName()
    {
        return "Punaise";
    }
}
