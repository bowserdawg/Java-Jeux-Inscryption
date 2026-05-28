package display;

public class BoardDisplayLogic {

    NewDisplay m_display;
    BoardPlaceHolder m_board;

    static final int BOARD_VERTICAL_SIZE = 42;
    static final int BOARD_HORIZONTAL_SIZE = 120;

    BoardDisplayLogic()
    {
        m_display = new NewDisplay(BOARD_HORIZONTAL_SIZE, BOARD_VERTICAL_SIZE);
    }

    public void afficher()
    {
        m_display.printRow("Partie"+ m_board.getNbPartie(), 0, 4);
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