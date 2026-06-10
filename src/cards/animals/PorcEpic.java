package cards.animals;

import cards.donnes.DonneFactory;
import cards.pouvoir.PiquesPointues;

public class PorcEpic extends AnimalCard {
    public PorcEpic() {
        super(DonneFactory.porcEpic());
        addPouvoir(new PiquesPointues());
    }

    @Override
    public String getName() {
        return "Porc-epic";
    }
}
