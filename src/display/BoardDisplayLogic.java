package display;

import cards.Card;
import cards.CardNull;
import logic.Board;
import logic.players.Rival;
import logic.players.User;

import java.util.Objects;
import java.util.Optional;

public class BoardDisplayLogic {

    // Utilisez pour l'affchage
    private Display m_display;
    // Le plateau ou les cartes sont en place.
    private Board m_board;
    private Rival m_rival;
    private User m_user;

    // Constantes

    static final int BOARD_VERTICAL_SIZE = 42;
    static final int BOARD_HORIZONTAL_SIZE = 120;

    static final int PADDING_LEFT = 4;
    static final int PADDING_CARDS = 9;

    static final int ENNEMI_ROW = 0;
    static final int MIDDLE_ROW = 1;
    static final int PLAYER_ROW = 2;


    static final int END_DISPLAY_CARD = 30;
    /**
     * Constructeur pour BoardDisplayLogic.
     * @param board Le plateau qui va etre afficher.
     */
    public BoardDisplayLogic(Board board, User user, Rival rival)
    {
        m_display = new Display(BOARD_VERTICAL_SIZE, BOARD_HORIZONTAL_SIZE);
        m_board = board;
        m_rival = rival;
        m_user = user;
    }

    public <T> T getOptionalValue(Optional<T> op)
    {
        if(op.isPresent())
        {
            return op.get();
        }
        else
        {
            throw new RuntimeException("Ce champ ne peux pas être obtenu");
        }
    }

    // Affiche l'instance actuelle du plateau
    public void afficher()
    {
        // Pour les cartes null
        CardNull nullCard = new CardNull();

        // mets tous l'écran à zero
        m_display.clearBuffer();

        // Pour se repérer dans les lignes.
        // row++ = on saute une ligne en plus
        int row = 0;

        m_display.printRow("Partie"+ m_board.getNbPartie(), row, PADDING_LEFT);
        row+=2; // Saut de ligne

        m_display.printRow(m_board.getNbTour() + "er Tour:", row, PADDING_LEFT);
        row+=2;

        final int colMax = m_board.getHeight();
        final int rowMax = m_board.getWidth();

        final int colMul = Card.s_HORIZONTAL_SIZE;
        final int rowMul = Card.s_VERTICAL_SIZE;

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
                m_display.draw(Objects.requireNonNullElse(c, nullCard), row + i * 8, j + PADDING_CARDS);

                if(i == ENNEMI_ROW)
                {
                    m_display.printRow("||", (row + 8)+rowMul, j + PADDING_CARDS + (PADDING_CARDS/2) + 1);
                    m_display.printRow("\\/", (row + 8)+rowMul+1, j + PADDING_CARDS + (PADDING_CARDS/2) + 1);
                }
                j += colMul + 1;
            }
            row++;
        }

        row = END_DISPLAY_CARD;

        m_display.printRow("  Votre main :", row, 0);
        row++;

        var playerArray = m_user.getHand();
        var len = playerArray.size();
        for(int i = 0; i < len; ++i)
        {
            Card c =  playerArray.get(i);
            var life = c.getLife();
            var att  = getOptionalValue(c.getAttaque());
            var bloodDrop = getOptionalValue(c.getBloodDrops());
            var bone = getOptionalValue(c.getBones());

            m_display.printRow(
                    (i+1) + ". " +
                    c.getName() +
                    " PV: " + life + "    " +
                    " Att: " + att + "    " +
                    " Gouttes de sang: " + bloodDrop +
                    " Os " + bone, row, PADDING_LEFT);
            row++;
        }
        row++;
        row++;
        row++;

        m_display.printRow("Actions possibles:", row, 0);
        row++;
        m_display.printRow("[fin] Terminer votre tour:", row, 2);
        row++;
        m_display.printRow("[piocher] Piocher une carte[piocher] Piocher une carte:", row, 2);
        row++;
        m_display.printRow("[placer <numero carte> <position>] Placer une carte sur le plateau:", row, 2);
        row++;

        m_display.printRow("Score " + m_board.getScore(), 0, 60);
        m_display.printRow("Pioche",  END_DISPLAY_CARD, 70);
        m_display.printRow("Pioche",  END_DISPLAY_CARD, 70);
        m_display.draw(nullCard, END_DISPLAY_CARD + 1, 66);
        m_display.printRow("cartes",  END_DISPLAY_CARD+8, 65);

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