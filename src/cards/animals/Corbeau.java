package cards.animals;

import cards.donnes.DonneFactory;
import cards.interfaces.Flyable;

import java.util.HashMap;

public class Corbeau  extends AnimalCard implements Flyable {
    public Corbeau(){
        super(DonneFactory.corbeau());
    }

    @Override
    public String getName()
    {
        return "Corbeau";
    }
}
