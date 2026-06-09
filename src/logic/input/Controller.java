package logic.input;

import cards.Card;
import cards.CostType;
import cards.animals.AnimalCard;
import logic.Board;
import logic.DrawReason;
import logic.players.Player;
import logic.GameStatus;
import logic.CantPlaceReason;

import java.util.ArrayList;
import java.util.Optional;

public class Controller {
    private final Board m_board;
    private final Player m_player;
    private GameStatus m_status = GameStatus.OK;
    private boolean m_alreadyDraw = false;
    private CantPlaceReason m_placeReason = CantPlaceReason.OK;
//

    public Controller(Board board, Player player) {
        m_board = board;
        m_player = player;
    }

    public CantPlaceReason canPlace(int cardToPlace) {
        // Si le joueur a assez d'os et de cartes à sacrifier pour placer sa carte alors, on renvoie true.
        Optional<AnimalCard> tempCard = m_player.getCardFromHand(cardToPlace);
        if (tempCard.isPresent()) {
            AnimalCard card = tempCard.get();
            if (!hasEnoughBlood(card)) {
                return CantPlaceReason.NOT_ENOUGH_BLOOD;
            } else if (!hasEnoughBone(card)) {
                m_placeReason = CantPlaceReason.NOT_ENOUGH_BONES;
                return CantPlaceReason.NOT_ENOUGH_BONES;
            } else {
                m_placeReason = CantPlaceReason.OK;
                return CantPlaceReason.OK;
            }
        }
        return CantPlaceReason.EMPTY_CARD;
    }

    public DrawReason drawCard() {
        if (!m_alreadyDraw) {
            m_player.drawCard();
            m_status = GameStatus.REFRESH;
            return DrawReason.OK;
        } else {
            return DrawReason.ALREADY_DRAW;
        }
    }

    public DrawReason drawSquirrel() {
        if (!m_alreadyDraw) {

            m_player.drawSquirrel();
            m_status = GameStatus.REFRESH;
            return DrawReason.OK;
        } else {
            return DrawReason.ALREADY_DRAW;
        }
    }

    public boolean placeCard(int column, int cardIndex) {
        m_status = GameStatus.REFRESH;
        return m_player.placeCard(column, cardIndex);
    }

    public boolean hasEnoughBlood(AnimalCard playerCard) {
        int sacrifiableCard = m_board.getNbSacrifiableCards(Board.PositionsRow.UserRow);
        int cardCost;
        if (playerCard.getBloodDrops().isPresent()) {
            cardCost = playerCard.getBloodDrops().get();
            return cardCost <= sacrifiableCard;
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
        m_status = GameStatus.REFRESH;
    }

    public GameStatus getGameStatus() {
        return m_status;
    }
//
//    public CantPlaceReason getPlacingReason() {
//        return m_placeReason;
//    }

    public void sacrificeCards(ArrayList<Integer> cardsColumns) {
        for (int cardColumn : cardsColumns) {
            m_board.removeCard(Board.PositionsRow.UserRow, cardColumn);
        }
        m_status = GameStatus.REFRESH;
    }

    public boolean canSacrifice(int cardToPlace) {
        if (m_player.getCardFromHand(cardToPlace).isPresent()) {
            AnimalCard card = m_player.getCardFromHand(cardToPlace).get();
            switch (card.getCostType()) {
                case BLOOD_DROPS:
                    if (hasEnoughBlood(card)) {
                        return true;
                    } else {
                        m_status = GameStatus.CANT_PLACE;
                        m_placeReason = CantPlaceReason.NOT_ENOUGH_BLOOD;
                    }
                    break;
                case BONES:
                    if (!hasEnoughBone(card)) {
                        return true;
                    } else {
                        m_status = GameStatus.CANT_PLACE;
                        m_placeReason = CantPlaceReason.NOT_ENOUGH_BONES;
                    }

                    break;
            }
        }
        return false;
    }

    // retourne true si la carte est gratuit false sinon
    public boolean isCardFree(int cardToPlace) {
        Optional<AnimalCard> tempCard = m_player.getCardFromHand(cardToPlace);
        if (tempCard.isPresent()) {
            AnimalCard card = tempCard.get();
            return card.getCostType() == CostType.FREE;
        }
        return false;
    }

    public int costOf(int cardToPlace) {
        Optional<AnimalCard> tempCard = m_player.getCardFromHand(cardToPlace);
        if (tempCard.isPresent()) {
            AnimalCard card = tempCard.get();
            // Pas besoin de verifier si l'optional n'est pas vide parce qu'on est sur qu'il ne l'est pas
            switch (card.getCostType()) {
                case BLOOD_DROPS:
                    return card.getBloodDrops().get();
                case BONES:
                    return card.getBones().get();
            }
        }
        return 0;
    }

    public boolean isSacrifiable(int column) {
        return m_board.isCardSacrifiable(Board.PositionsRow.UserRow, column);
    }

    public void endGame() {
        m_status = GameStatus.STOPPED;
    }
}
