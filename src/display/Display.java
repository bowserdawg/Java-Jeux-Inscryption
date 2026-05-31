package display;

import cards.Card;

public class Display {

    public Display()
    {
    }

    private static void print(String string)
    {
        System.out.println(string);
    }

    private void drawCardTopOrBottom(Card card)
    {
        if(card != null)
        {
            System.out.print("*-----------*");
        }
        else
        {
            System.out.print("*************");
        }
    }

    static private final int s_cardWidth = 13;
    static private final int s_cardHeight = 7;

    static private final int s_topLine = 0;
    static private final int s_cardNameLine = 1;
    static private final int s_nameSeparationLine = 2;
    static private final int s_pvLine = 3;
    static private final int s_attackLine = 4;
    static private final int s_emptyLine = 5;
    static private final int s_lastLine = 6;

    private void printCardInfoLine(String info)
    {
        System.out.print("| ");
        System.out.print(info);
        for(int i = 0; i < ( info.length() - (s_cardWidth-3) ); i++)
            System.out.print(" ");
        System.out.print("|");
    }

    private void drawCardInside(Card card, int number)
    {
        if(card == null)
        {
            if(number == 1 || number == s_cardHeight-1)
            {
                System.out.print("*************");
            }
            else
            {
                System.out.print("*           *");
            }
            return;
        }

        String nom = card.getName();

        switch (number)
        {
            case s_topLine:
                System.out.print("*-----------*");
                break;
            case s_cardNameLine:
                printCardInfoLine(card.getName());
                break;
            case s_nameSeparationLine:
                System.out.print("|-----------|");
                break;
            case s_pvLine:
                printCardInfoLine("PV : " + card.getLife());
                break;
            case s_attackLine:
//                printCardInfoLine("Att : " + card.getData().get(
//                        Card.DONNE_TYPE[Card.DONNE_TYPE_ELEMS.ATTAQUE.ordinal()]
//                ));
                break;
            case s_emptyLine:
                System.out.print("|           |");
                break;
            case s_lastLine:
                System.out.print("*-----------*");
                break;
            default:
                System.out.print("Error");
                break;
        }
    }

    private void drawPaddingLeft() { System.out.print("        "); }
    private void drawPaddingBetweenCard() { System.out.print("   ");}

    private void drawCardLine(Card one, Card two, Card three, Card four)
    {
        Card[] array = new Card[] {one, two, three, four};

        for(int i = 0; i < s_cardHeight; i++)
        {
            drawPaddingLeft();
            if(i == 0 || i == s_cardHeight-1)
            {
                for(Card c : array)
                {
                    drawCardTopOrBottom(c);
                    drawPaddingBetweenCard();
                }
            }
            else
            {
                for(Card c: array)
                {
                    drawCardInside(c, i);
                    drawPaddingBetweenCard();
                }
            }

            print("");
        }
    }

    public void draw(BoardPlaceHolder b)
    {
        // Partie N
        print("Partie " + b.getNbPartie());
        // Neme tour
        if(b.getNbPartie() > 1) { print(b.getNbPartie() + "eme Tour:"); }
        else { print(b.getNbPartie() + "er Tour"); }


        for(int i = 0; i < BoardPlaceHolder.BOARD_LIGNE_ARRAY.length; i++)
        {
            drawCardLine(
                    b.getCard(
                            BoardPlaceHolder.BOARD_LIGNE_ARRAY[i],
                            BoardPlaceHolder.BoardColonne.FIRST_ROW),
                    b.getCard(
                            BoardPlaceHolder.BOARD_LIGNE_ARRAY[i],
                            BoardPlaceHolder.BoardColonne.SECOND_ROW),
                    b.getCard(
                            BoardPlaceHolder.BOARD_LIGNE_ARRAY[i],
                            BoardPlaceHolder.BoardColonne.THIRD_ROW),
                    b.getCard(
                            BoardPlaceHolder.BOARD_LIGNE_ARRAY[i],
                            BoardPlaceHolder.BoardColonne.FOURTH_ROW)
            );

            if(i == 0)
            {
                //       ||              ||              ||              ||
                //       \/              \/              \/              \/
                print("               ||              ||              ||              ||");
                print("               \\/              \\/              \\/              \\/");
            }
            else
            {
                print("\n");
            }
        }
    }


}
