package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Coyote extends AnimalCard{
    public Coyote(){
        super(DonneFactory.coyote());
    }

    @Override
    public String getName()
    {
        return "Coyotte";
    }
}
