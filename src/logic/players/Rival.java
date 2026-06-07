package logic.players;

import cards.animals.AnimalCard;
import logic.Board;

import java.util.ArrayList;
import java.util.Optional;

public class Rival extends Player{
    public Rival(CardStack stack, Board board){
        super(stack, board);
    }
    
    /**
     * Place une carte sur la ligne de l'adversaire.
     * @param column La colonne où placer la carte
     * @param indexCardInHand L'indice de la carte contenue dans la main
     * @return Vrai si la carte à pu être placée. Faux sinon.
     */
    @Override
    public boolean placeCard(int column, int indexCardInHand) {
        
        if (!getHand().isEmpty()) {
            Optional<AnimalCard> tempCard = super.getCardFromHand(indexCardInHand);
            AnimalCard selectedCard;
            if (tempCard.isPresent()) {
                selectedCard = tempCard.get();
                if (getBoard().placeCard(Board.Positions.NextRivalRow, column, selectedCard)) {
                    return removeCardFromHand(selectedCard);
                }
            }
            return false;
            
        } else {
            return false;
        }
    }

    // C C C C
    // C C C C
    // C C C C

    // 0 0 0 0

    public boolean autoPlaceCard(Board board)
    {
        ArrayList<AnimalCard> hand = getHand();
        if(hand == null)
           return false;


        ArrayList<Integer> costPlace = new ArrayList<>();

        super.drawCard();
        return true;
    }
}
