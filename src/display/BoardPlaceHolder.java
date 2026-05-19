package display;

import cards.Card;

public class BoardPlaceHolder {

    public enum BoardX
    {
        ENNEMI,
        MIDDLE,
        PLAYER,
    }

    public enum BoardY
    {
        FIRST_ROW,
        SECOND_ROW,
        THIRD_ROW,
        FOURTH_ROW,
    }

    private Display display;
    private int nbPartie;
    private int nbTour;

    private Card[][] cards = new Card[3][4];

    public int getNbPartie() {
        return nbPartie;
    }

    public int getNbTour() {
        return nbTour;
    }

    public Card getCard(BoardX x, BoardY y)
    {
        return cards[x.ordinal()][y.ordinal()];
    }


}
