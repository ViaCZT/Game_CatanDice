package comp1110.ass2;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * player can do actions:
 * build is put a road,settlement,city,joker on the board
 * trade is use gold to get other resources
 * swap is use joker to change the resources the player get
 *
 * @author Zetian Chen   uid: u7564812
 */
public class Player {
//    private String board_state;
//    private String resource_state;

    public Player() {
    }

    /**
     * To get the type of action according to an Action String.
     *
     * @param action: e.g. "swap 0 1", "build J5", "trade 2"
     * @return: only 3 types: "build", "trade", "swap" (all Strings)
     */
    public String getActionType(String action) {
        return action.split(" ")[0];
    }

    /**
     * To get all the resources which can be traded by 2 gold, i.e. all resources except 5(gold)
     *
     * @return: a list that contains all the resources can be traded by 2 gold.
     */
    public List<String> getTradeResource() {
        String[] trade = {"1", "2", "3", "4", "0"};
        return Arrays.asList(trade);
    }

    /**
     * To get all the resources which can be used to swap, i.e. all resources
     *
     * @return: a list that contains all the resources can be used to swap.
     */
    public List<String> getSwapResource() {
        String[] swap = {"1", "2", "3", "4", "5", "0"};
        return Arrays.asList(swap);
    }

    /**
     * Roll the specified number of dice and add the result to the resource state.
     * This method does not return any value. It should update the given resource_state.
     *
     * @param n_dice:            The number of dice to roll (>= 0).
     * @param resource_state：The available resources that the dice roll will be added to.
     */
    public void rollDice(int n_dice, int[] resource_state) {
        Random ran = new Random();
//        ran.nextInt(5);
        for (int i = 1; i <= n_dice; i++) {
            int resource_index = ran.nextInt(5);
            resource_state[resource_index]++;
        }
    }
}
