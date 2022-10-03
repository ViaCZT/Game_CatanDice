package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    //The string representation of the board state.
    //looks like "R1,S4,R4,C7"
    private final String board_state;

    public Board(String board_state) {
        this.board_state = board_state;
    }

    public String[] getMove() {
        return board_state.split(",");
    }

    public List<String> getAllElements() {
        String[] s = {"", "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15",
                "S3", "S4", "S5", "S7", "S9", "S11",
                "C7", "C12", "C20", "C30",
                "J1", "J2", "J3", "J4", "J5", "J6",
                "K1", "K2", "K3", "K4", "K5", "K6"};
        return Arrays.asList(s);
    }

    public List<String> getBoardState() {
        return new ArrayList<>(Arrays.asList(getMove()));
    }

}
