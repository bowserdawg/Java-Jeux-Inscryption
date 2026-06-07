package logic.players;

import cards.animals.AnimalCard;
import logic.Board;

import java.util.Optional;

public class User extends Player {
    public User(CardStack stack, Board board) {
        super(stack, board);
    }
    
    /**
     * Place une carte sur la ligne de l'utilisateur.
     * @param column La colonne où placer la carte
     * @param indexCardInHand L'indice de la carte contenue dans la main
     * @return Vrai si la carte a pu être placée. Faux sinon.
     */
    
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
