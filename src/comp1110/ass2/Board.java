package comp1110.ass2;

/**
 * The board for each player.
 *
 * @author Zetian Chen (uid: u7564812)
 */
public class Board {
    private final String board_state;
    //The string representation of the board state, eg."R1,S4,R4,C7"

    public Board(String board_state) {
        this.board_state = board_state;
    }

    /**
     * To get all the structures that have built by now.
     *
     * @return: an array containing all the structures that have built by now.
     */
    public String[] getStructure() {
        return board_state.split(",");
    }





}
