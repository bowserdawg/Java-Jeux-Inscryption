package logic;

import cards.animals.*;
import display.BoardDisplayLogic;
import logic.input.Controller;
import logic.input.Input;
import logic.players.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameInit {
    private static final ArrayList<AnimalCard>  m_defaultStack = new ArrayList<>(Arrays.asList(new Loup(), new Hermine(), new Ecureuil(), new Punaise(), new Loup()));

    private Board m_board;
    private final User m_user;
    private final Rival m_rival;
    private final CardStack m_userStack;
    private final CardStack m_rivalStack;
    private final Input m_input;
    private final Controller m_controller;
    private final BoardDisplayLogic m_display;

    public GameInit() {
        m_board = new Board();
        m_userStack = new CardStack(m_defaultStack);
        m_rivalStack = new CardStack(m_defaultStack);
        m_user = new User(m_userStack, m_board);
        m_rival = new Rival(m_rivalStack, m_board);
        m_controller = new Controller(m_board, m_user);
        m_input = new Input(m_controller);
        m_display = new BoardDisplayLogic(m_board, m_user, m_rival);

    }

    /**
     * Augmente le nombre de parties.
     */
    public void newPartie()
    {
        int nbPartie = m_board.getNbPartie();
        m_board = new Board(nbPartie);
    }

    public Board getBoard() {
        return m_board;
    }

    public Controller getUserController() {
        return m_controller;
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
