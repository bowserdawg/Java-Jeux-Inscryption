package cards.donnes;

import cards.types.Regular;
import cards.types.Volant;

public class DonneFactory {

    public static CardDonne chat()
    {
        return new CardDonne(0, 1, 1, 0, new Regular());
    }

    public static CardDonne grizzly()
    {
        return new CardDonne(4, 6, 3, 0, new Regular());
    }

    public static CardDonne coyote()
    {
        return new CardDonne(2, 1, 0, 4, new Regular());
    }

    public static CardDonne moineau()
    {
        return new CardDonne(1, 2, 1, 0, new Volant());
    }

    public static CardDonne corbeau()
    {
        return new CardDonne(2, 3, 2, 0, new Volant());
    }

    public static CardDonne ecureuil()
    {
        return new CardDonne(2, 3, 2, 0, new Regular());
    }

    public static CardDonne hermine()
    {
        return new CardDonne(1, 3, 1, 0, new Regular());
    }

    public static CardDonne louveteau()
    {
        return new CardDonne(1, 1, 1, 0, new Regular());
    }

    public static CardDonne loup()
    {
        return new CardDonne(3, 2, 2, 0, new Regular());
    }

    public static CardDonne punaise()
    {
        return new CardDonne(1, 2, 0, 2, new Regular());
    }

    public static CardDonne rocher()
    {
        return new CardDonne(5);
    }

    public static CardDonne sapin()
    {
        return new CardDonne(5);
    }

}
