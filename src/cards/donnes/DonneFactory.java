package cards.donnes;

import cards.interfaces.Flyable;

public class DonneFactory {

    public CardDonne chat()
    {
        return CardDonne.withPvAttBloodAndBone(0, 1, 1, 0);
    }

    public CardDonne grizzly()
    {
        return CardDonne.withPvAttBloodAndBone(4, 6, 3, 0);
    }

    public CardDonne coyote()
    {
        return CardDonne.withPvAttBloodAndBone(2, 1, 0, 4);
    }

    public CardDonne moineau()
    {
        return CardDonne.withPvAttTypeBloodAndBone(1, 2, Flyable.nom, 1, 0);
    }



}
