package cards.animals;

import cards.donnes.DonneFactory;
import cards.pouvoir.Coureur;

public class Elan extends AnimalCard {
    public Elan() {
        super(DonneFactory.elan());
        addPouvoir(new Coureur());
    }

    @Override
    public String getName() {
        return "Elan";
    }
}
