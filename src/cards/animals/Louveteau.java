package cards.animals;

import cards.donnes.DonneFactory;

import java.util.HashMap;

public class Louveteau extends AnimalCard {
    public Louveteau(){
        super(DonneFactory.louveteau());
    }

    @Override
    public String getName()
    {
        return "Louveteau";
    }
}
