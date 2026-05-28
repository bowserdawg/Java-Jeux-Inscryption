package display;

public class BoardDisplayLogic {

    NewDisplay m_display;

    static final int BOARD_VERTICAL_SIZE = 42;
    static final int BOARD_HORIZONTAL_SIZE = 120;

    BoardDisplayLogic()
    {
        m_display = new NewDisplay(BOARD_HORIZONTAL_SIZE, BOARD_VERTICAL_SIZE);
    }

    public void afficher()
    {

    }
}
