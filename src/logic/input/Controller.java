package logic.input;

import cards.Card;
import cards.animals.AnimalCard;
import logic.Board;
import logic.players.Player;
import logic.GameStatus;
import logic.CantPlaceReason;

public class Controller {
    private final Board m_board;
    private final Player m_player;
    private GameStatus m_status = GameStatus.OK;
    private CantPlaceReason m_placeReason = CantPlaceReason.OK;
    
    
    public Controller(Board board, Player player) {
        m_board = board;
        m_player = player;
    }
    
    public GameStatus canPlace(AnimalCard cardToPlace) {
        
        // Si le joueur a assez d'os et de cartes à sacrifier pour placer sa carte alors, on renvoie true.
//        return hasEnoughBlood(cardToPlace) || hasEnoughBone(cardToPlace);
        if (!hasEnoughBlood(cardToPlace)) {
            m_placeReason = CantPlaceReason.NOT_ENOUGH_BLOOD;
            return GameStatus.CANT_PLACE;
        } else if (!hasEnoughBone(cardToPlace)) {
            m_placeReason = CantPlaceReason.NOT_ENOUGH_BONES;
            return GameStatus.CANT_PLACE;
        } else {
            return  GameStatus.OK;
        }
        
    }
    
    public void drawCard() {
        m_player.drawCard();
    }
    
    public void drawSquirrel() {
        m_player.drawSquirrel();
    }
    
    public void placeCard(int column, int cardIndex) {
        
        if (m_player.getCardFromHand(cardIndex).isPresent()) {
            AnimalCard cardToPlace = m_player.getCardFromHand(cardIndex).get();
            
            if (canPlace(cardToPlace) == GameStatus.OK) {
                m_player.placeCard(column, cardIndex);
                m_status = GameStatus.PLACED;
            }
            else{ m_status = GameStatus.CANT_PLACE;}
        }
    }
    
    public boolean hasEnoughBlood(AnimalCard playerCard) {
        int maxBloodDrops = 0;
        for (int i = 0; i < m_board.getWidth(); i++) {
            Card boardCard = m_board.getCard(Board.PositionsRow.UserRow, i);
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
    
    public void passTurn() {
        m_board.passTurn();
        m_status = GameStatus.PASSED_TURN;
    }
    
    public GameStatus getGameStatus() {
        return m_status;
    }
    
    public CantPlaceReason getPlacingReason() {
        return m_placeReason;
    }
    
    public void sacrificeCards(int[] cardsColumns){
        for(int cardColumn : cardsColumns){
            Card card = m_board.getCard(Board.PositionsRow.UserRow, cardColumn);
            if(!card.isSacrifiable()){
                m_status = GameStatus.CANT_SACRIFIE;
                break;
            }
            else {
                m_board.sacrificeCardAt(Board.PositionsRow.UserRow, cardColumn);
            }
        }
    }
}
