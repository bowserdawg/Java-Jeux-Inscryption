package logic.input;

import cards.Card;
import cards.animals.AnimalCard;
import logic.Board;
import logic.players.Player;

import java.util.ArrayList;

public class Controller {
    private final Board m_board;
    private final Player m_player;

    public Controller(Board board, Player player) {
        m_board = board;
        m_player = player;
    }

    public boolean canPlace(AnimalCard cardToPlace) {

        // Si le joueur a assez d'os et de cartes à sacrifier pour placer sa carte alors, on renvoie true.
        return hasEnoughBlood(cardToPlace) || hasEnoughBone(cardToPlace);
    }

    public void drawCard(){
        m_player.drawCard();
    }

    public void drawSquirrel(){
        m_player.drawSquirrel();
    }

    public void placeCard(int column, int cardIndex) {
        ArrayList<AnimalCard> playerHand = m_player.getHand();
        AnimalCard cardToPlace;
        if (playerHand.size() - 1 >= cardIndex) {
            cardToPlace = playerHand.get(cardIndex);
            if (canPlace(cardToPlace)) {
                m_player.placeCard(column,cardIndex);
            }
        }
    }

    public boolean hasEnoughBlood(AnimalCard playerCard) {
        int maxBloodDrops = 0;
        for (int i = 0; i < m_board.getWidth(); i++) {
            Card boardCard = m_board.getCard(Board.Positions.UserRow, i);
            if (boardCard != null) {
                if (boardCard.isSacrifiable()) {
                    maxBloodDrops++;
                }
            }
        }
        int cardCost;
        if (playerCard.getBloodDrops().isPresent()) {
            cardCost = playerCard.getBloodDrops().get();
            return cardCost <= maxBloodDrops;
        }
        return false;
    }

    public boolean hasEnoughBone(AnimalCard card) {
        int playerBones = m_player.getRemainingBones();
        int cardCost;
        if (card.getBones().isPresent()) {
            cardCost = card.getBones().get();
            return cardCost <= playerBones;
        }
        return false;
    }

    public void passTurn(){
        m_board.passTurn();
    }
}
