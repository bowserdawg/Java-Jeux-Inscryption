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
        m_display = new NewDisplay(BOARD_HORIZONTAL_SIZE, BOARD_VERTICAL_SIZE);
        m_board = board;
    }

    public void afficher()
    {
        m_display.clearBuffer();


        m_display.printRow("Partie"+ m_board.getNbPartie(), 0, PADDING_LEFT);

        m_display.printRow(m_board.getNbTour() + "er Tour:", 2, PADDING_LEFT);

        for(int i = 0; i < BoardPlaceHolder.BOARD_LIGNE_ARRAY.length; i++) {

            Card c1 = m_board.getCard(BoardPlaceHolder.BOARD_LIGNE_ARRAY[i], BoardPlaceHolder.BoardColonne.FIRST_COL);
            Card c2 = m_board.getCard(BoardPlaceHolder.BOARD_LIGNE_ARRAY[i], BoardPlaceHolder.BoardColonne.SECOND_COL);
            Card c3 = m_board.getCard(BoardPlaceHolder.BOARD_LIGNE_ARRAY[i], BoardPlaceHolder.BoardColonne.THIRD_COL);
            Card c4 = m_board.getCard(BoardPlaceHolder.BOARD_LIGNE_ARRAY[i], BoardPlaceHolder.BoardColonne.FOURTH_COL);

            Card[] cards = {c1, c2, c3, c4};

            for(Card c : cards)
            {

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