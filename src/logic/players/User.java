package logic.players;

import cards.animals.AnimalCard;
import logic.Board;

import java.util.Optional;

public class User extends Player {
    public User(CardStack stack, Board board) {
        super(stack, board);
    }
    
    @Override
    public boolean placeCard(int column, int indexCardInHand) {
        if (!getHand().isEmpty()) {
            Optional<AnimalCard> tempCard = getCardFromHand(indexCardInHand);
            AnimalCard selectedCard;
            if (tempCard.isPresent()) {
                selectedCard = tempCard.get();
                if (getBoard().placeCard(Board.Positions.UserRow, column, selectedCard)) {
                    return removeCardFromHand(selectedCard);
                }
            }
            return false;
            
        } else {
            return false;
        }
    }
}
