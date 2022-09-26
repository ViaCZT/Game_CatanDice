package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUpdateResourceState {
    private String errorPrefix(String action,int[] resource) {
        return "CatanDice.updateResourceState(" + action + "," + toString(resource)+")";
    }

    private String toString(int[] resource){
        return "{"+resource[0]+", "+resource[1]+", "+resource[2]+", "+resource[3]+", "+resource[4]+", "+resource[5]+"}";
    }

    private void test(int[] input, String action, int[] expected) {
        String errorPrefix = errorPrefix(action,input);
        int[] new_input = new int[6];
        System.arraycopy(input, 0, new_input, 0, 6);
        int[] out = CatanDice.updateResourceState(action, new_input);
        assertEquals(toString(expected), toString(out), errorPrefix);
    }

    static String[] builds = {"build R2", "build S5", "build C30", "build J2"};
    static String[] swaps = {"swap 2 0", "swap 1 4", "swap 3 1"};
    static String[] trades = {"trade 2", "trade 3"};

    private final static int[][] resources = {
            {0, 0, 1, 1, 1, 3},
            {0, 1, 1, 1, 1, 2},
            {3, 2, 0, 1, 0, 0},
            {1, 2, 2, 0, 1, 0}
    };
    private final static int[][] buildexpected = {
            {0, 0, 1, 0, 0, 3},
            {0, 0, 0, 0, 0, 2},
            {0, 0, 0, 1, 0, 0},
            {0, 1, 1, 0, 1, 0}
    };
    private final static int[][] swapexpected = {
            {1, 0, 0, 1, 1, 3},
            {0, 0, 1, 1, 2, 2},
            {3, 3, 0, 0, 0, 0}
    };
    private final static int[][] tradeexpected = {
            {0, 0, 2, 1, 1, 1},
            {0, 1, 1, 2, 1, 0}
    };

    @Test
    public void testbuild() {
        for (int i = 0; i <= 3; i++) {
            test(resources[i], builds[i], buildexpected[i]);
        }
    }
    @Test
    public void testswap() {
        for (int i = 0; i <= 2; i++) {
            test(resources[i], swaps[i], swapexpected[i]);
        }
    }
    @Test
    public void testtrade() {
        for (int i = 0; i <= 1; i++) {
            test(resources[i], trades[i], tradeexpected[i]);
        }
    }

    public static void main(String[] args) {
        TestUpdateResourceState tests = new TestUpdateResourceState();
        System.out.println("testing...");
        tests.testbuild();
        tests.testtrade();
        tests.testswap();
        System.out.println("all done!");
    }
}