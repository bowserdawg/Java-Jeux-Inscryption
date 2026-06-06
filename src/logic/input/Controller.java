package logic.input;

import logic.Board;
import logic.players.Player;

public class Controller {
    private Board m_board;

    public Controller(Board board, Player player){
            m_board = board;
    }

    public boolean canPlace(Board.Positions row, int column){

        return false;
    }
}
