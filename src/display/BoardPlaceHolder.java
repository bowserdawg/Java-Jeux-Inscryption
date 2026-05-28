package display;

import cards.Card;
import cards.animals.*;
import cards.obstacles.Pin;
import cards.obstacles.Rock;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class BoardPlaceHolder {

    // Les coordonéees des lignes dans la Board
    public enum BoardLigne
    {
        ENNEMI,
        MIDDLE,
        PLAYER,
        BOAR_X_LEN,
    }
    public final static BoardLigne[] BOARD_LIGNE_ARRAY = {
            BoardLigne.ENNEMI,
            BoardLigne.MIDDLE,
            BoardLigne.PLAYER
    };

    // Les coordonéee des colonnes dans la Board
    public enum BoardColonne
    {
        FIRST_COL,
        SECOND_COL,
        THIRD_COL,
        FOURTH_COL,
        BOARD_COL_LEN,
    }
    public final static BoardColonne[] BOARD_COLONNES_ARRAY = {
            BoardColonne.FIRST_COL,
            BoardColonne.SECOND_COL,
            BoardColonne.THIRD_COL,
            BoardColonne.FOURTH_COL
    };

    private Display m_display;
    private int m_nbPartie;
    private int m_nbTour;

    // Ligne / Colonne
    private Card[][] cards = new Card[3][4];

    public BoardPlaceHolder()
    {
        m_display = new Display();
        m_nbPartie = 1;
        m_nbTour = 1;
    }

    public int getNbPartie() {
        return m_nbPartie;
    }

    public int getNbTour() {
        return m_nbTour;
    }

    // Peut retourner null
    public Card getCard(int row, int col)
    {
        if(row < 0 || col < 0)
            throw new IndexOutOfBoundsException("L'index ne peut pas être négative");

        if(row > cards.length || col > cards[0].length)
            throw new IndexOutOfBoundsException("L'index est plus grand que le tableau");

        return cards[row][col];
    }

    public int getBoardRowLen()
    {
        return cards[0].length;
    }

    public int getBoardColumnLen()
    {
        return cards.length;
    }

    public void display()
    {
        m_display.draw(this);
    }

    public void setCardsTemp()
    {
        cards[0][0] = new Louveteau();
        cards[0][1] = new Punaise();
        cards[0][2] = new Moineau();
        cards[0][3] = new Chat();

        cards[1][0] = null;
        cards[1][1] = new Rock();
        cards[1][2] = new Pin();
        cards[1][3] = null;

        cards[2][0] = new Corbeau();
        cards[2][1] = new Ecureuil();
        cards[2][2] = new Loup();
        cards[2][3] = new Hermine();
    }
}
