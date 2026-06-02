package cards.animals;

import cards.donnes.DonneFactory;

public class Corbeau extends FlyingAnimal {
    public Corbeau(){
        super(DonneFactory.corbeau());
    }

    @Override
    public String getName()
    {
        return "Corbeau";
    }
}
