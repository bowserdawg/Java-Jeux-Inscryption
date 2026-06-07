import cards.animals.*;
import logic.input.Input;


public class Main {
    public static void main(String[] args) {
        System.out.println("Another challenger... It has been ages.");

        // initialisation d'une partie
        GameInit game = new GameInit(); // utilisation d'une classe pour facilement initialiser une partie
        Input i = game.getInput();
        do {

//            System.out.println(game.getUser().getNbCards());
            game.getDisplay().afficher();
        }
        while (i.promptAction());

    }
}