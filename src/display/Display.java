package display;

import display.interfaces.Displayable;

import java.util.Arrays;

/**
 * Crée un écran de caractère qui peuvent être affiché dans le terminal.
 */
public class Display {

    private int m_numberOfRows; // 45
    // Plus grand que prevus juste pour etre sûr.
    private int m_sizeOfARow; // 120

    private char[][] m_screenBuffer;

    public Display(int numberOfRows, int sizeOfARow)
    {
        if(numberOfRows < 0 || sizeOfARow < 0)
            throw new IndexOutOfBoundsException("Les valeurs d'initialisation ne peuvent pas être negatives");

        m_numberOfRows = numberOfRows;
        m_sizeOfARow = sizeOfARow;

        m_screenBuffer = new char[m_numberOfRows][m_sizeOfARow];
        clearBuffer();
    }

    // Valeur par défaut 50 row 120 col
    public Display()
    {
        m_numberOfRows = 50;
        m_sizeOfARow = 120;

        m_screenBuffer = new char[m_numberOfRows][m_sizeOfARow];
        clearBuffer();
    }

    // Permet d'écrire sur une ligne un string.
    public void printRow(String string, int row, int start)
    {
        if (string.length()+start >= m_sizeOfARow)
            throw new IndexOutOfBoundsException("Il est impossible d'écrire ce string sur le tableau");
        if(row < 0 || start < 0)
            throw new IndexOutOfBoundsException("Les parametres 'row' ou 'start' ne peuvent pas etre negative");
        if(row >= m_numberOfRows)
            throw new IndexOutOfBoundsException("Le parametre 'row' est beaucoup trop grand");;

        int i = 0;
        while(i < string.length())
        {
            m_screenBuffer[row][start] = string.charAt(i);

            ++i;
            ++start;
        }
    }

    // Permet d'écrire sur une ligne un tableau de char.
    public void printRow(char[] arr, int row, int start)
    {
        if (arr.length+start > m_sizeOfARow)
            throw new IndexOutOfBoundsException("Il est impossible d'écrire ce string sur le tableau");
        if(row < 0 || start < 0)
            throw new NegativeArraySizeException("Les parametres 'row' ou 'start' ne peuvent pas etre negative");
        if(row >= m_numberOfRows)
            throw new IndexOutOfBoundsException("Le parametre 'row' est beaucoup trop grand");;

        int i = 0;
        while(i < arr.length)
        {
            m_screenBuffer[row][start] = arr[i];

            ++i;
            ++start;
        }
    }

    /**
     * Permet d'afficher un objet de type displayable sur l'écran.
     * Peut lancer une Exception si
     * @param displayable L'objet qui va être affiché.
     * @param row La ligne ou commence l'affichage, comme l'abscisse Y.
     * @param column La colonne ou commence l'affichage, comme l'abscisse X.
     * @return Un boolean indiquant si l'opération a échoué ou non.
     */
    public void draw(Displayable displayable, int row, final int column)
    {
        char[][] disForm = displayable.getDisplayableForm();

        for(char[] arr : disForm)
        {
            printRow(arr, row, column);
            ++row;
        }
    }

    // Remplir tous les tableaux avec ' '
    public void clearBuffer()
    {
        for (char[] row : m_screenBuffer) {
            Arrays.fill(row, ' ');
        };
    }

    /**
     * Affiche le tableau dans l'ecran.
     */
    public void printBufferToScreen()
    {
        for(char[] buf : m_screenBuffer)
        {
            System.out.println(String.valueOf(buf));
        }
    }

    /**
     * Efface tout l'écran avec un clear.
     * Ne marche que dans les systèmes UNIX par contre.
     *
     * ET ne marche pas sur le terminal d'IntelliJ.
     */
    public void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Donne une ligne.
     *
     * @param num Le numero de la ligne.
     *
     * @return La colonne demande.
      */
    public char[] getRow(int num)
    {
        if(num >= m_numberOfRows || num < 0)
            throw new IndexOutOfBoundsException("Le ligne demandé n'existe pas");

        return m_screenBuffer[num];
    }
}
