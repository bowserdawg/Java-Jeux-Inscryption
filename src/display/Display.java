package display;

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

    public void draw(BoardPlaceHolder b)
    {
        // Partie N
        print("Partie " + itos(b.getNbPartie()));
        //
        print("\n");
        // Neme tour
        if(b.getNbPartie() > 1) { print(itos(b.getNbPartie()) + "eme Tour"); }
        else { print(itos(b.getNbPartie()) + "er Tour"); }


    }


}
