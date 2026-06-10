package cards.animals;

import cards.donnes.DonneFactory;
import cards.pouvoir.ContactMortel;

public class Vipere extends AnimalCard {
    public Vipere() {
        super(DonneFactory.vipere());
        addPouvoir(new ContactMortel());
    }

    @Override
    public String getName() {
        return "Vipere";
    }
}
