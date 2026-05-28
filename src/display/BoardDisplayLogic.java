package display;

import cards.Card;

public class BoardDisplayLogic {

    private NewDisplay m_display;
    private BoardPlaceHolder m_board;

    static final int BOARD_VERTICAL_SIZE = 42;
    static final int BOARD_HORIZONTAL_SIZE = 120;

    static final int PADDING_LEFT = 4;

    BoardDisplayLogic(BoardPlaceHolder board)
    {
        m_display = new NewDisplay(BOARD_VERTICAL_SIZE, BOARD_HORIZONTAL_SIZE);
        m_board = board;
    }

    public void afficher()
    {
        m_display.clearBuffer();


        m_display.printRow("Partie"+ m_board.getNbPartie(), 0, PADDING_LEFT);

        m_display.printRow(m_board.getNbTour() + "er Tour:", 2, PADDING_LEFT);

        final int colMax = m_board.getBoardColumnLen();
        final int rowMax = m_board.getBoardRowLen();

        final int colMul = Card.s_HORIZONTAL_SIZE;
        final int rowMul = Card.s_VERTICAL_SIZE;

        /*for(int row = 0; row < colMax; ++row)
        {
            for(int col = 0; col < rowMax; ++col)
            {
                Card c = m_board.getCard(row, col);
                if(c != null)
                    m_display.draw(c, col*rowMul, col*colMul + PADDING_LEFT);
            }
        }*/

        for(int i = 0; i < 3; ++i)
        {
            Card[] theCards = {m_board.getCard(i, 0),
                    m_board.getCard(i, 1),
                    m_board.getCard(i, 2),
                    m_board.getCard(i, 3)
            };
            int j = 0;
            for(Card c : theCards)
            {
                if(c != null)
                    m_display.draw(c, i*8, j);
                j += 14;
            }
        }

        m_display.printBufferToScreen();
    }
}

/*
    Partie 1

    1er Tour:

         *-----------*   *************   *-----------*   *************
         | Louveteau |   *           *   | Moineau   |   *           *
         |-----------|   *           *   |-----------|   *           *
         | PV: 1     |   *           *   | PV: 1     |   *           *
         | Att: 1    |   *           *   | Att : 1   |   *           *
         |           |   *           *   | Volant    |   *           *
         *-----------*   *************   *-----------*   *************
               ||              ||              ||              ||
               \/              \/              \/              \/
         *************   *************   *************   *************
         *           *   *           *   *           *   *           *
         *           *   *           *   *           *   *           *
         *     A1    *   *     A2    *   *     A3    *   *     A4    *
         *           *   *           *   *           *   *           *
         *           *   *           *   *           *   *           *
 Score   *************   *************   *************   *************
   0
         *************   *-----------*   *************   *************
         *           *   | Rocher    |   *           *   *           *
         *           *   |-----------|   *           *   *           *
         *     B1    *   | PV: 5     |   *    B3     *   *     B4    *
         *           *   |           |   *           *   *           *
         *           *   |           |   *           *   *           *
         *************   *-----------*   *************   *************
                                                                              Pioche
  Votre main :                                                             *-----------*
    1. Ecureuil   PV: 1     Att: 0    Gouttes de sang: 0  Os : 0           |           |
    2. Ecureuil   PV: 1     Att: 0    Gouttes de sang: 0  Os : 0           |           |
    3. Hermine    PV: 3     Att: 1    Gouttes de sang: 1  Os : 0           |     11    |
    4. Ecureuil   PV: 1     Att: 0    Gouttes de sang: 0  Os : 0           |   cartes  |
                                                                           |           |
                                                                           *-----------*

Actions possibles:
  [fin] Terminer votre tour
  [piocher] Piocher une carte
  [placer <numero carte> <position>] Placer une carte sur le plateau

$ placer 2 B1

 */