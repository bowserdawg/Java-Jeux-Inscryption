package cards.animals;

import cards.donnes.DonneFactory;
import cards.interfaces.Flyable;

import java.util.HashMap;

public class Moineau extends AnimalCard implements Flyable {
    public Moineau(){
        super(DonneFactory.moineau());
    }


    @Override
    public String getName()
    {
        return "Moineau";
    }
}
