package cards.donnes;

import cards.types.Regular;
import cards.types.Volant;

public class DonneFactory {
    
    public static CardDonne chat() {
        return new CardDonne(1, 0, 1, 0, new Regular());
    }
    
    public static CardDonne grizzly() {
        return new CardDonne(6, 4, 3, 0, new Regular());
    }
    
    public static CardDonne coyote() {
        return new CardDonne(1, 2, 0, 4, new Regular());
    }
    
    public static CardDonne moineau() {
        return new CardDonne(2, 1, 1, 0, new Volant());
    }
    
    public static CardDonne corbeau() {
        return new CardDonne(3, 2, 2, 0, new Volant());
    }
    
    public static CardDonne ecureuil() {
        return new CardDonne(1, 0, 0, 0, new Regular());
    }
    
    public static CardDonne hermine() {
        return new CardDonne(3, 1, 1, 0, new Regular());
    }
    
    public static CardDonne louveteau() {
        return new CardDonne(1, 1, 1, 0, new Regular());
    }
    
    public static CardDonne loup() {
        return new CardDonne(2, 3, 2, 0, new Regular());
    }
    
    public static CardDonne punaise() {
        return new CardDonne(2, 1, 0, 2, new Regular());
    }
    
    public static CardDonne rocher() {
        return new CardDonne(5);
    }
    
    public static CardDonne sapin() {
        return new CardDonne(3);
    }
    
    public static CardDonne elan() {
        return new CardDonne(4, 2, 2, 0, new Regular());
    }
    
    public static CardDonne vipere() {
        return new CardDonne(1, 1, 2, 0, new Regular());
    }
    
    public static CardDonne porcEpic() {
        return new CardDonne(2, 1, 1, 0, new Regular());
    }

    public static cards.animals.AnimalCard randomAnimalCard() {
        java.util.List<java.util.function.Supplier<cards.animals.AnimalCard>> suppliers = java.util.Arrays.asList(
            cards.animals.Chat::new,
            cards.animals.Corbeau::new,
            cards.animals.Coyote::new,
            cards.animals.Ecureuil::new,
            cards.animals.Elan::new,
            cards.animals.Grizzly::new,
            cards.animals.Hermine::new,
            cards.animals.Loup::new,
            cards.animals.Louveteau::new,
            cards.animals.Moineau::new,
            cards.animals.PorcEpic::new,
            cards.animals.Punaise::new,
            cards.animals.Vipere::new
        );
        int index = new java.util.Random().nextInt(suppliers.size());
        return suppliers.get(index).get();
    }
}


