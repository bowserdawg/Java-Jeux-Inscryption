package logic.players;

import cards.Card;
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
                if (getBoard().placeCard(Board.PositionsRow.NextRivalRow, column, selectedCard)) {
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

    /**
     * Fonction d'aide qui donne la meilleure carte possible pour le rival dans la main.
     * @param hand La main du Rival.
     * @return La carte.
     */
    private static AnimalCard getBestAnimalCard(ArrayList<AnimalCard> hand)
    {
        AnimalCard chosenCard = hand.getFirst();
        int bestChoosingModifier = 0;   // Modifier positive → plus grand = mieux
        for(AnimalCard a : hand)
        {
            Optional<Integer> att =  a.getAttaque();

            int attaque = 0;
            if(att.isPresent())
                attaque = att.get();

            int choosingModifier = a.getLife() + attaque;

            if(choosingModifier > bestChoosingModifier)
            {
                bestChoosingModifier = choosingModifier;
                chosenCard = a;
            }
        }
        return chosenCard;
    }

    /**
     * Place automatiquement une carte dans le board si c'est possible.
     * @return Si l'opération a abouti ou non.
     */
    public boolean autoPlaceCard()
    {
        Board board = getBoard();

        AnimalCard drawnCard;
        ArrayList<AnimalCard> hand = getHand();

        // Pas d'écureuil → pioche un écureuil.
        // Écureuil → pioche carte.
        boolean hasSquirrel = false;
        for(AnimalCard c : hand)
        {
            if(c.getName().equals("Ecureuil"))
                hasSquirrel = true;
        }

        boolean canDraw = true;
        
        if(hasSquirrel)
            canDraw = drawCard();
        
        if(!hasSquirrel)
            canDraw = drawSquirrel();
        
        if(!canDraw)
            return false;

        // Update le hand
        hand = getHand();
        // Vu qu'on a forcément une carte minimum.
        AnimalCard chosenCard = getBestAnimalCard(hand);

        // Choisir le meilleur emplacement pour la carte
        int boardWidth = board.getWidth();
        
        int bestPlaceTileIndex = -1;
        int bestPlaceTileModifier = 9999999;  // Modifier négative → plus petite = mieux
        for(int i = 0; i < boardWidth; ++i)
        {
            Card myCard = board.getCard(Board.PositionsRow.NextRivalRow, i);
            Card theirCard = board.getCard(Board.PositionsRow.UserRow, i);
            
            if(myCard == null)
            {
                int currentPlaceTileModifier = 0;
                if(theirCard != null)
                {
                    currentPlaceTileModifier = theirCard.getLife();
                }
                
                if(currentPlaceTileModifier < bestPlaceTileModifier)
                {
                    bestPlaceTileModifier = currentPlaceTileModifier;
                    bestPlaceTileIndex = i;
                }
            }
        }
        
        if(bestPlaceTileIndex == -1)
        {
            return false;
        }
        board.placeCard(Board.PositionsRow.NextRivalRow, bestPlaceTileIndex, chosenCard);
        
        return true;
    }
}
