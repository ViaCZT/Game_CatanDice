package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestUpdateBoardState {
    private String errorPrefix(String action, String board_state) {
        return "CantanDice.updateBoardState(" + action + ", {" + board_state + "})";
    }

    @Test
    public void testBuild() {
        String[] actions = {"build R0", "build R12", "build S3", "build S11", "build C20", "build J5"};
        String[] board_state = {"R1,S4,R4,C7", "R0,R4,J1", "C12", "R12,S9", "R6", "C30"};
        String[] board_state_return = {"R1,S4,R4,C7,R0", "R0,R4,J1,R12", "C12,S3", "R12,S9,S11", "R6,C20", "C30,J5"};
        for (int i = 0; i < actions.length; i++) {
            String actual = CatanDice.updateBoardState(actions[i], board_state[i]);
            String errorPrefix = errorPrefix(actions[i], board_state[i]);
            Assertions.assertEquals(board_state_return[i], actual, errorPrefix);
        }
    }

    @Test
    public void testSwap() {
        String[] actions = {"swap 0 1", "swap 0 3", "swap 0 2", "swap 2 1", "swap 2 3", "swap 2 5"};
        String[] board_state = {"R1,S4,J2,J4", "R0,J1,R4,J6", "C12,J6", "R12,J2,S9,J6", "J6,R6,J4", "J6,J3,C30"};
        //when there are specific joker and J6 at the same time, swap the specific joker.
        String[] board_state_return = {"R1,S4,K2,J4", "R0,J1,R4,K6", "C12,K6", "R12,K2,S9,J6", "J6,R6,K4", "K6,J3,C30"};
        for (int i = 0; i < actions.length; i++) {
            String actual = CatanDice.updateBoardState(actions[i], board_state[i]);
            String errorPrefix = errorPrefix(actions[i], board_state[i]);
            Assertions.assertEquals(board_state_return[i], actual, errorPrefix);
        }
    }

    public static void main(String[] args) {
        TestUpdateBoardState tests = new TestUpdateBoardState();
        System.out.println("testing updateBoardState method testBuild()...");
        tests.testBuild();
        System.out.println("testing updateBoardState method testSwap()...");
        tests.testSwap();
        System.out.println("all done!");
    }

}
