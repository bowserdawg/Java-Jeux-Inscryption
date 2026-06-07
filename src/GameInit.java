import cards.animals.*;
import display.BoardDisplayLogic;
import logic.Board;
import logic.input.Controller;
import logic.input.Input;
import logic.players.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameInit {
    private static final ArrayList<AnimalCard>  m_defaultStack = new ArrayList<>(Arrays.asList(new Loup(), new Hermine(), new Ecureuil(), new Ecureuil(), new Loup()));

    private final Board m_board;
    private final User m_user;
    private final Rival m_rival;
    private final CardStack m_userStack;
    private final CardStack m_rivalStack;
    private final Input m_input;
    private final Controller m_userController;
    private final Controller m_rivalController;
    private final BoardDisplayLogic m_display;

    public GameInit() {
        m_board = new Board();
        m_userStack = new CardStack(m_defaultStack);
        m_rivalStack = new CardStack(m_defaultStack);
        m_user = new User(m_userStack, m_board);
        m_rival = new Rival(m_rivalStack, m_board);
        m_userController = new Controller(m_board, m_user);
        m_rivalController = new Controller(m_board, m_rival);
        m_input = new Input(m_userController);

        m_display = new BoardDisplayLogic(m_board, m_user, m_rival);
    }

    public Board getBoard() {
        return m_board;
    }

    public Controller getUserController() {
        return m_userController;
    }

    public Controller getRivalController() {
        return m_rivalController;
    }

    public CardStack getUserStack() {
        return m_userStack;
    }

    public CardStack getRivalStack() {
        return m_rivalStack;
    }

    public User getUser() {
        return m_user;
    }

    public Rival getRival() {
        return m_rival;
    }

    public Input getInput() {
        return m_input;
    }

    public BoardDisplayLogic getDisplay() {
        return m_display;
    }
}
