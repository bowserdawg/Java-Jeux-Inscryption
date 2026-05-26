package logic.players;

import cards.Card;
import cards.animals.AnimalCard;
import cards.animals.Ecureuil;

public class CardStack {
    public AnimalCard drawCard(){
        // retourne Ecureuil pour l'instant
        return new Ecureuil();
    };
}
