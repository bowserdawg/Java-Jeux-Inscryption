package display;

import cards.Card;
import cards.CardNull;
import display.interfaces.Displayable;
import logic.Board;
import logic.BoneProperties;
import logic.players.Rival;
import logic.players.User;

import java.util.ArrayList;
import java.util.Optional;

public class BoardDisplayLogic {

    // Utilisez pour l'affchage
    private final Display m_display;
    // Le plateau ou les cartes sont en place.
    private final Board m_board;
    private final Rival m_rival;
    private final User m_user;

    // Constantes

    static final int BOARD_VERTICAL_SIZE = 48;
    static final int BOARD_HORIZONTAL_SIZE = 120;

    static final int PADDING_LEFT = 4;
    static final int PADDING_CARDS = 9;

    static final int ENNEMI_ROW = 0;
    static final int MIDDLE_ROW = 1;
    static final int PLAYER_ROW = 2;

    final static int ACTION_TITLE_PADDING = 0;
    final static int ACTIONS_PADDING = 2;

    static final int SCORE_COL = 60;
    static final int SCORE_ROW = 0;

    final int PIOCHE_COL_PADDING = 3;

    static final int END_DISPLAY_CARD = 33;

    final int CARD_WIDTH  = Card.s_HORIZONTAL_SIZE;
    final int CARD_HEIGHT = Card.s_VERTICAL_SIZE;
    
    
    final private Board.PositionsRow[] colArr = {
            Board.PositionsRow.NextRivalRow,
            Board.PositionsRow.RivalRow,
            Board.PositionsRow.UserRow
    };
    
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

    /**
     * Affiche le bloc qui contient les informations sur le nombre de parties et de tour.
     * @param row La ligne ou le bloc va etre affiché.
     * @return La ligne suivante.
     */
    private int head(int row)
    {
        m_display.printRow("Partie"+ m_board.getNbPartie(), row, PADDING_LEFT);
        row+=2; // Saut de ligne

        m_display.printRow((m_board.getNbTour()+1) + "er Tour:", row, PADDING_LEFT);
        row+=2;

        return row;
    }

    /**
     * Affiche une colonne de QUATRE cartes.
     * @param boardRow Ligne du Board qui va être affiché.
     * @param rowCordName Nom du coordonné Y de la carte 'A','B'...
     *                    Les coordonnés X est évalué dans cette fonction.
     * @param row Ligne où la ligne va être affiché.
     * @return Ligne suivante ou un autre bloc ou le meme bloc peut être re-affiché.
     */
    private int cardRow(int boardRow, char rowCordName , int row)
    {
        // Les coordonnées X de la colonne vont être évalué avec cette variable.
        char colCordName = '1';

        ArrayList<Card> theCards = new ArrayList<>();
        int width = m_board.getWidth();
        
        for(int i = 0; i < width; ++i)
        {
            theCards.add(m_board.getCard(colArr[boardRow], i));

        }

        int j = 0;
        for(Card c : theCards)
        {
            // Pour les cartes null
            CardNull nullCard = new CardNull();

            if(c == null)
                m_display.draw(nullCard, row + boardRow * (CARD_HEIGHT+1), j + PADDING_CARDS);

            if(boardRow == MIDDLE_ROW || boardRow == PLAYER_ROW)
            {
                m_display.printRow(
                        new char[]{rowCordName, colCordName},
                        row + boardRow * (CARD_HEIGHT+1) + CARD_HEIGHT / 2,
                        j + PADDING_CARDS + CARD_WIDTH / 2
                );
                colCordName++;
            }

            if(c != null)
                m_display.draw(c,row + boardRow * (CARD_HEIGHT+1), j + PADDING_CARDS);

            if(boardRow == ENNEMI_ROW)
            {
                m_display.printRow("||", (row + CARD_HEIGHT), j + PADDING_CARDS + (PADDING_CARDS/2) + 1);
                m_display.printRow("\\/", (row + (CARD_HEIGHT+1)), j + PADDING_CARDS + (PADDING_CARDS/2) + 1);
            }

            j += CARD_WIDTH + 1;
        }
        row++;

        return row;
    }

    /**
     * Affiche le bloc pour le
     * @param row  Ligne où le bloc va être affiché.
     * @param paddingLeft  Le padding de gauche du bloc.
     * @return La ligne suivante.
     */
    private int hand(int row, int paddingLeft)
    {
        m_display.printRow("  Votre main :", row, 0);
        row++;

        var playerArray = m_user.getHand();
        var len = playerArray.size();
        for(int i = 0; i < len; ++i)
        {

            Card c =  playerArray.get(i);
            String name = "%-10s".formatted(c.getName());
            var life = c.getLife();
            var att  = getOptionalValue(c.getAttaque());
            var bloodDrop = getOptionalValue(c.getBloodDrops());
            var bone = getOptionalValue(c.getBones());

            StringBuilder cardStr = new StringBuilder(
                    (i+1) + ". " +
                            name +
                            " PV: " + life + ' ' +
                            " Att: " + att + ' ' +
                            " Sang: " + bloodDrop + ' ' +
                            " Os: " + bone
            );
            if (!c.getPouvoirs().isEmpty()) {
                cardStr.append(" Pouvoir: ");
                for (int pIdx = 0; pIdx < c.getPouvoirs().size(); pIdx++) {
                    cardStr.append(c.getPouvoirs().get(pIdx).getNom());
                    if (pIdx < c.getPouvoirs().size() - 1) {
                        cardStr.append(", ");
                    }
                }
            }

            m_display.printRow(
                    cardStr.toString(),

                    row,
                    paddingLeft);
            row++;
        }
        //    1. Ecureuil PV: 1	 Att: 0	 Gouttes de sang: 0	 Os 0
        //
        row++;
        row++;
        return ++row;
    }

    /**
     * Le bloc qui affiche les actions.
     * @param row La position y du bloc.
     * @return La prochaine ligne.
     */
    private int actions(int row)
    {
        m_display.printRow("Actions possibles:", row, ACTION_TITLE_PADDING);
        row++;
        m_display.printRow("[fin] Terminer votre tour:", row, ACTIONS_PADDING);
        row++;
        m_display.printRow("[piocher] Piocher une carte:", row, ACTIONS_PADDING);
        row++;
        m_display.printRow("[placer <numero carte> <position>] Placer une carte sur le plateau:", row, 2);
        return ++row;
    }

    /**
     * Le bloc qui affiche le score.
     * @param row La position y du bloc.
     * @param col La position x du bloc.
     * @return La prochaine ligne.
     */
    private int score(int row, int col)
    {
        m_display.printRow("Score " + m_board.getScore(), row, col);

        return row;
    }

    /**
     * Permet d'afficher la pioche.
     * @param displayable La carte qui va être affiché pour indiquer la pioche .
     * @param row La position y du bloc.
     * @param col La position x du bloc.
     * @return La prochaine ligne.
     */
    private int pioche(Displayable displayable, int row, int col)
    {

        int remainingCards = m_user.remainingCards();

        m_display.printRow("Pioche",  row, col + PIOCHE_COL_PADDING);
        m_display.draw(displayable, row + 1, col);
        m_display.printRow(remainingCards + " carte(s)",  row + CARD_HEIGHT+1, col + PIOCHE_COL_PADDING-1);

        col += CARD_WIDTH + PADDING_LEFT;

        remainingCards = m_user.remainingSquirrels();

        m_display.printRow("Pioche ecureuil",  row, col-1);
        m_display.draw(displayable, row + 1, col);
        m_display.printRow(remainingCards + " carte(s)",  row + CARD_HEIGHT+1, col + PIOCHE_COL_PADDING-1);

        return row;
    }

    public int bones(int row, int col)
    {
        m_display.printRow(
                "Quantité d'Os : " + m_board.getRemainingBones(BoneProperties.USER),
                row,
                col
        );
        row++;

        return row;
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

        // Appelle à this dans cette partie,
        // car ça montre que c'est une fonction plus explicitement.
        row = this.head(row);

        char rowCordName = 'A' - 1;

        int height = m_board.getHeight();
        for(int i = 0; i < height; ++i)
        {
            row = this.cardRow(i, rowCordName, row);
            rowCordName++;
        }

        row = END_DISPLAY_CARD;

        row = this.hand(row, PADDING_LEFT);

//        row = this.actions(row);

        this.score(SCORE_ROW, SCORE_COL);

        this.pioche(nullCard, END_DISPLAY_CARD, 85);

        this.bones(41, 0);

        m_display.printBufferToScreen();
    }
}

// Réferences

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