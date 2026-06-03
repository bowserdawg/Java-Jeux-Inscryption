package logic.players;

import cards.animals.AnimalCard;
import logic.Board;

import java.util.Optional;

public class Rival extends Player{
    
    public Rival(CardStack stack, Board board){
        super(stack, board);
    }
    
    @Override
    public boolean placeCard(int column, int indexCardInHand) {
        
        if (!getHand().isEmpty()) {
            Optional<AnimalCard> tempCard = getCardFromHand(indexCardInHand);
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
}
