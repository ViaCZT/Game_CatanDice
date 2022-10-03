package comp1110.ass2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Player {
    // player can do actions:
    // build is put a road,settlement,city,joker on the board
    // trade is use gold to get other resources
    // swap is use joker to change the resources the player get

    public Player() {
    }

    // only 3 types: "build", "trade", "swap"
    public String getActionType(String action) {
        return action.split(" ")[0];
    }

    public List<String> getTradeResource() {
        String[] trade = {"1", "2", "3", "4", "0"};
        return Arrays.asList(trade);
    }

    public List<String> getSwapResource() {
        String[] swap = {"1", "2", "3", "4", "5", "0"};
        return Arrays.asList(swap);
    }

    public static void rollDice(int n_dice, int[] resource_state) {
        Random ran = new Random();
//        ran.nextInt(5);
        for (int i = 1; i <= n_dice; i++) {
            int resource_index = ran.nextInt(5);
            resource_state[resource_index]++;
        }
    }
}
