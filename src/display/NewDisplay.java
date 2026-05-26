package display;

import display.interfaces.Displayable;

import java.util.Arrays;

public class NewDisplay {

    static final int ROW_MAX_LEN = 43;
    // Plus grand que prevus juste pour etre sûr.
    static final int COL_MAX_LEN = 120;

    private char[][] m_screenBuffer;

    public NewDisplay()
    {
        m_screenBuffer = new char[ROW_MAX_LEN][COL_MAX_LEN];
        clearBuffer();
    }

    // Permet d'écrire sur une ligne un string.
    public void printRow(String string, int row, int start)
    {
        if (string.length()+start > COL_MAX_LEN)
            throw new IndexOutOfBoundsException("Il est impossible d'écrire ce string sur le tableau");
        if(row < 0 || start < 0)
            throw new IndexOutOfBoundsException("Les parametres 'row' ou 'start' ne peuvent pas etre negative");

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
        if (arr.length+start > COL_MAX_LEN)
            throw new IndexOutOfBoundsException("Il est impossible d'écrire ce string sur le tableau");
        if(row < 0 || start < 0)
            throw new NegativeArraySizeException("Les parametres 'row' ou 'start' ne peuvent pas etre negative");

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
    public void draw(Displayable displayable, int row, int column)
    {
        char[][] disForm = displayable.getDisplayableFormArray();
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

    public void printBuffer()
    {
        for(char[] buf : m_screenBuffer)
        {
            for(char c : buf)
            {
                System.out.print(c);
            }
            System.out.print("\n");
        }
    }

}
