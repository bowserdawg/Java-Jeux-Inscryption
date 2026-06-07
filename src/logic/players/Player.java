package logic.players;

import cards.animals.AnimalCard;
import cards.animals.Ecureuil;
import logic.Board;

import java.util.ArrayList;
import java.util.Optional;

public abstract class Player {
    private ArrayList<AnimalCard> m_hand = new ArrayList<>();
    private Board m_board;
    private int m_remainingBones = 0;
    private CardStack m_stack;

    public Player(CardStack stack, Board board) {
        for (int i = 0; i < 4; i++) {
            Optional<AnimalCard> tempCard = stack.drawCard();
            if (tempCard.isPresent()) {
                m_hand.add(tempCard.get());
            } else {
                break;
            }
        }
        m_board = board;
        m_stack = stack;
    }

    public boolean placeCard(int column, int indexCardInHand) {
        return true;
    }

    public Board getBoard() {
        return m_board;
    }

    public ArrayList<AnimalCard> getHand() {
        return m_hand;
    }

    public int getNbCards() {
        return m_hand.size();
    }

    public Optional<AnimalCard> getCardFromHand(int index) {
        if (m_hand.size() - 1 <= index) {
            return Optional.empty();
        }
        AnimalCard selectedCard = m_hand.get(index);
        return Optional.of(selectedCard);
    }

    public boolean removeCardFromHand(AnimalCard card) {
        if (!m_hand.contains(card)) {
            return false;
        }
        m_hand.remove(card);
        return true;
    }

    /**
     *
     * @param nbBonesToRemove Le nombre d'os à retirer
     * @return Le nombre d'os restants
     */

    public int removeBones(int nbBonesToRemove) {
        m_remainingBones -= nbBonesToRemove;
        return m_remainingBones;
    }

    /**
     *
     * @param nbBonesToAdd Le nombre d'os à ajouter
     * @return Le nombre d'os restants
     */

    public int addBones(int nbBonesToAdd) {
        m_remainingBones += nbBonesToAdd;
        return m_remainingBones;
    }

    public int getRemainingBones() {
        return m_remainingBones;
    }

    public boolean drawCard() {
        Optional<AnimalCard> op = m_stack.drawCard();

        if (op.isEmpty()) {
            return false;
        }

        m_hand.add(op.get());

        return true;
    }

    public boolean drawSquirrel() {
        Optional<Ecureuil> tempCard = m_stack.drawSquirrel();
        if (tempCard.isPresent()) {
            Ecureuil squirrel = tempCard.get();
            m_hand.add(squirrel);
            return true;
        }
        return false;
    }
}
