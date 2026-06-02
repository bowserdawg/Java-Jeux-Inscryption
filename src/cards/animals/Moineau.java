package cards.animals;

import cards.donnes.DonneFactory;

public class Moineau extends FlyingAnimal {
    public Moineau(){
        super(DonneFactory.moineau());
    }


    @Override
    public String getName()
    {
        return "Moineau";
    }
}
