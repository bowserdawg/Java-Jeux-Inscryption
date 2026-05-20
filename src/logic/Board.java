package display;

import cards.Card;
import cards.animals.Chat;

public class BoardPlaceHolder {

    // Les coordonéees X dans la Board
    public enum BoardX
    {
        ENNEMI,
        MIDDLE,
        PLAYER,
        BOAR_X_LEN,
    }
    public final static BoardX[] BOARD_X_ARRAY = {
            BoardX.ENNEMI,
            BoardX.MIDDLE,
            BoardX.PLAYER
    };

    // Les coordonéees Y dans la Board
    public enum BoardY
    {
        FIRST_ROW,
        SECOND_ROW,
        THIRD_ROW,
        FOURTH_ROW,
        BOARD_Y_LEN,
    }
    public final static BoardY[] BOARD_Y_ARRAY = {
            BoardY.FIRST_ROW,
            BoardY.SECOND_ROW,
            BoardY.THIRD_ROW,
            BoardY.FOURTH_ROW
    };

    private Display m_display;
    private int m_nbPartie;
    private int m_nbTour;

    private Card[][] cards = new Card[3][4];

    public BoardPlaceHolder()
    {
        m_display = new Display();
        m_nbPartie = 1;
        m_nbTour = 1;
    }

    public int getM_nbPartie() {
        return m_nbPartie;
    }

    public int getM_nbTour() {
        return m_nbTour;
    }

    // Peut retourner null
    public Card getCard(BoardX x, BoardY y)
    {
        return cards[x.ordinal()][y.ordinal()];
    }

    public void display()
    {
        m_display.draw(this);
    }

    public void setCardsTemp()
    {
        cards[0][0] = new Chat();
        cards[0][1] = new Chat();
        cards[0][2] = new Chat();
        cards[0][3] = new Chat();

        cards[1][0] = null;
        cards[1][1] = null;
        cards[1][2] = null;
        cards[1][3] = null;

        cards[2][0] = new Chat();
        cards[2][1] = new Chat();
        cards[2][2] = new Chat();
        cards[2][3] = new Chat();
    }
}
