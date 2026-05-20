package display;

import cards.Card;
import logic.Board;

public class Display {

    public Display()
    {
    }

    private static void print(String string)
    {
        System.out.println(string);
    }

    private static String itos(int integer)
    {
        return Integer.toString(integer);
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

    private void drawCardInside(Card card)
    {
        if(card != null)
        {
            System.out.print("|           |");
        }
        else
        {
            System.out.print("*           *");
        }
    }

    private void drawPaddingLeft() { System.out.print("        "); }
    private void drawPaddingBetweenCard() { System.out.print("   ");}

    static private final int s_cardWidth = 13;
    static private final int s_cardHeight = 7;
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
                    drawCardInside(c);
                    drawPaddingBetweenCard();
                }
            }

            print("");
        }
    }

    public void draw(Board b)
    {
        // Partie N
        print("Partie " + b.getM_nbPartie());
        // Neme tour
        if(b.getM_nbPartie() > 1) { print(b.getM_nbPartie() + "eme Tour:"); }
        else { print(b.getM_nbPartie() + "er Tour"); }


        for(int i = 0; i < Board.BOARD_X_ARRAY.length; i++)
        {
            drawCardLine(
                    b.getCard(
                            Board.BOARD_X_ARRAY[i],
                            Board.BoardY.FIRST_ROW),
                    b.getCard(
                            Board.BOARD_X_ARRAY[i],
                            Board.BoardY.SECOND_ROW),
                    b.getCard(
                            Board.BOARD_X_ARRAY[i],
                            Board.BoardY.THIRD_ROW),
                    b.getCard(
                            Board.BOARD_X_ARRAY[i],
                            Board.BoardY.FOURTH_ROW)
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
