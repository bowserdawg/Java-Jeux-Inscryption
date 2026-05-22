package display;

import cards.Card;
import logic.Board;

public class Display {
    
    public Display() {
    }
    
    private static void print(String string) {
        System.out.println(string);
    }
    
    private static String itos(int integer) {
        return Integer.toString(integer);
    }
    
    private void drawCardTopOrBottom(Card card) {
        if (card != null) {
            System.out.print("*-----------*");
        } else {
            System.out.print("*************");
        }
    }
    
    private void drawCardInside(Card card) {
        if (card != null) {
            System.out.print("|           |");
        } else {
            System.out.print("*           *");
        }
    }
    
    private void drawPaddingLeft() {
        System.out.print("        ");
    }
    
    private void drawPaddingBetweenCard() {
        System.out.print("   ");
    }
    
    static private final int s_cardWidth = 13;
    static private final int s_cardHeight = 7;
    
    private void drawCardLine(Card one, Card two, Card three, Card four) {
        Card[] array = new Card[]{one, two, three, four};
        
        for (int i = 0; i < s_cardHeight; i++) {
            drawPaddingLeft();
            if (i == 0 || i == s_cardHeight - 1) {
                for (Card c : array) {
                    drawCardTopOrBottom(c);
                    drawPaddingBetweenCard();
                }
            } else {
                for (Card c : array) {
                    drawCardInside(c);
                    drawPaddingBetweenCard();
                }
            }
            
            print("");
        }
    }
    
    public void draw(Board board) {
        // Partie N
        print("Partie " + board.getM_nbPartie());
        // Neme tour
        if (board.getM_nbPartie() > 1) {
            print(board.getM_nbPartie() + "eme Tour:");
        } else {
            print(board.getM_nbPartie() + "er Tour");
        }
        
        
        for (int row = 0; row < board.getHeight(); row++) {
            drawCardLine(
                    board.getCard(row, 0),
                    board.getCard(row, 1),
                    board.getCard(row, 2),
                    board.getCard(row, 3)
            );
            
            if (row == 0) {
                //       ||              ||              ||              ||
                //       \/              \/              \/              \/
                print("               ||              ||              ||              ||");
                print("               \\/              \\/              \\/              \\/");
            } else {
                print("\n");
            }
        }
    }
    
    
}
