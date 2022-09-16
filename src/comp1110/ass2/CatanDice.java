package comp1110.ass2;

import java.util.*;

public class CatanDice {

    /**
     * Check if the string encoding of a board state is well-formed.
     * Note that this does not mean checking if the state is valid
     * (represents a state that the player could get to in game play),
     * only that the string representation is syntactically well-formed.
     *
     * @param board_state: The string representation of the board state.
     * @return true iff the string is a well-formed representation of
     * a board state, false otherwise.
     */
    public static boolean isBoardStateWellFormed(String board_state) {
        // FIXME: Task #3
        String[] s = {"", "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15",
                "S3", "S4", "S5", "S7", "S9", "S11",
                "C7", "C12", "C20", "C30",
                "J1", "J2", "J3", "J4", "J5", "J6",
                "K1", "K2", "K3", "K4", "K5", "K6"};
        String[] boardStateSplit = board_state.split(",");
        int flag = 0;
        for (String move : boardStateSplit) {
            if (Arrays.asList(s).contains(move)) {
                flag++;
            }
        }
        return flag == boardStateSplit.length;

/*
        StringBuffer sb = new StringBuffer(board_state);
        int i = 0;
        if(board_state!=""){
            if(board_state.length()==2){
                sb.insert(1," ");
                String s2 = new String(sb);
                String[] s0 = s2.split(" ");
                if(s0.length==2){
                    char c =  s0[0].charAt(0);
                    if(c=='R'){
                        if(Integer.parseInt(s0[1])>=0&&Integer.parseInt(s0[1])<=15){
                            i++ ;
                        }
                    }
                    else if (c=='S'){
                        if(Integer.parseInt(s0[1])==3||Integer.parseInt(s0[1])==4||Integer.parseInt(s0[1])==5||Integer.parseInt(s0[1])==7||Integer.parseInt(s0[1])==9||Integer.parseInt(s0[1])==11){
                            i++;
                        }
                    }
                    else if (c=='C'){
                        if(Integer.parseInt(s0[1])==7||Integer.parseInt(s0[1])==12||Integer.parseInt(s0[1])==20||Integer.parseInt(s0[1])==30){
                            i++;
                        }
                    }
                    else if (c=='J'||c=='K'){
                        if(Integer.parseInt(s0[1])>=1&&Integer.parseInt(s0[1])<=6){
                            i++;
                        }
                    }
                }
            }


        }
        else i++;

        if (i==1){
            return true;
        }
        else return false;
*/
    }

    /**
     * Check if the string encoding of a player action is well-formed.
     *
     * @param action: The string representation of the action.
     * @return true iff the string is a well-formed representation of
     * a board state, false otherwise.
     */
    public static boolean isActionWellFormed(String action) {
        // FIXME: Task #4
        String[] trade = {"1", "2", "3", "4", "0"};
        String[] swap = {"1", "2", "3", "4", "5", "0"};
        String[] actionSplit = action.split(" ");
        int flag = 0;
        if (Objects.equals(actionSplit[0], "build")) {
            if (isBoardStateWellFormed(actionSplit[1])) {
                flag++;
            }
        } else if (actionSplit[0].equals("trade") && actionSplit.length == 2) {
            if (Arrays.asList(trade).contains(actionSplit[1])) {
                flag++;
            }
        } else if (actionSplit[0].equals("swap") && actionSplit.length == 3) {
            if (Arrays.asList(swap).contains(actionSplit[1]) &&
                    Arrays.asList(swap).contains(actionSplit[2])) {
                flag++;
            }
        }
        return flag == 1;
    }

    /**
     * Roll the specified number of dice and add the result to the
     * resource state.
     * <p>
     * The resource state on input is not necessarily empty. This
     * method should only _add_ the outcome of the dice rolled to
     * the resource state, not remove or clear the resources already
     * represented in it.
     *
     * @param n_dice:         The number of dice to roll (>= 0).
     * @param resource_state: The available resources that the dice
     *                        roll will be added to.
     *                        <p>
     *                        This method does not return any value. It should update the given
     *                        resource_state.
     */
    public static void rollDice(int n_dice, int[] resource_state) {
        // FIXME: Task #6
        Random ran = new Random();
//        ran.nextInt(5);
        for (int i = 1; i <= n_dice; i++) {
            int resource_index = ran.nextInt(5);
            resource_state[resource_index]++;
        }
    }

    /**
     * Check if the specified structure can be built next, given the
     * current board state. This method should check that the build
     * meets the constraints described in section "Building Constraints"
     * of the README file.
     *
     * @param structure:   The string representation of the structure to
     *                     be built.
     * @param board_state: The string representation of the board state.
     * @return true iff the structure is a possible next build, false
     * otherwise.
     */
    public static boolean checkBuildConstraints(String structure, String board_state) {
        // FIXME: Task #8
        String[] Roads = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15"};
        String[] Settlements = {"S3", "S4", "S5", "S7", "S9", "S11"};
        String[] Cities = {"C7", "C12", "C20", "C30"};
        String[] Jokers = {"J1", "J2", "J3", "J4", "J5", "J6"};
        String[] Knights = {"K1", "K2", "K3", "K4", "K5", "K6"};
        String[] boardStateSplit = board_state.split(",");
/*
        ArrayList boardStateList = new ArrayList();
        ArrayList rList = new ArrayList();
        ArrayList sList = new ArrayList();
        ArrayList cList = new ArrayList();
        ArrayList jList = new ArrayList();
        ArrayList kList = new ArrayList();

        Collections.addAll(boardStateList, boardStateSplit);
        Collections.addAll(rList, Roads);
        Collections.addAll(sList, Settlements);
        Collections.addAll(cList, Cities);
        Collections.addAll(jList, Jokers);
        Collections.addAll(kList, Knights);
*/
        List<String> boardStateList = new ArrayList<>(Arrays.asList(boardStateSplit));
        List<String> rList = new ArrayList<>(Arrays.asList(Roads));
        List<String> sList = new ArrayList<>(Arrays.asList(Settlements));
        List<String> cList = new ArrayList<>(Arrays.asList(Cities));
        List<String> jList = new ArrayList<>(Arrays.asList(Jokers));
        List<String> kList = new ArrayList<>(Arrays.asList(Knights));

        int flag = 0;
        if (rList.contains(structure)) {
            switch (structure) {
                case "R0":
                    flag++;
                    break;
                case "R2":
                    if (boardStateList.contains("R0")) {
                        flag++;
                    }
                    break;
                case "R5":
                    if (boardStateList.contains("R3")) {
                        flag++;
                    }
                    break;
                case "R12":
                    if (boardStateList.contains("R7")) {
                        flag++;
                    }
                    break;
                default:
                    if (boardStateList.contains(rList.get(rList.indexOf(structure) - 1))) {
                        flag++;
                    }
                    break;
            }
        } else if (sList.contains(structure)) {
            if (!structure.equals("S3")) {
                if (boardStateList.contains(sList.get(sList.indexOf(structure) - 1)) && boardStateList.contains(getRoad(structure))) {
                    flag++;
                }
            } else {
                if (boardStateList.contains(getRoad(structure))) {
                    flag++;
                }
            }
        } else if (cList.contains(structure)) {
            if (!structure.equals("C7")) {
                if (boardStateList.contains(cList.get(cList.indexOf(structure) - 1)) && boardStateList.contains(getRoad(structure))) {
                    flag++;
                }
            } else {
                if (boardStateList.contains(getRoad(structure))) {
                    flag++;
                }
            }
        } else if (jList.contains(structure)) {
            if (structure.equals("J1")) {
                flag++;
            } else {
                if (boardStateList.contains(jList.get(jList.indexOf(structure) - 1)) || boardStateList.contains(kList.get(jList.indexOf(structure) - 1))) {
                    flag++;
                }
            }
        }
        return flag == 1;
    }

    public static String getRoad(String structure) {
        String Road;
        switch (structure) {
            case "S4" -> Road = "R2";
            case "S5" -> Road = "R5";
            case "S7" -> Road = "R7";
            case "S9" -> Road = "R9";
            case "S11" -> Road = "R11";
            case "C7" -> Road = "R1";
            case "C12" -> Road = "R4";
            case "C20" -> Road = "R13";
            case "C30" -> Road = "R15";
            default -> Road = "";
        }
        return Road;
    }


    /**
     * Check if the available resources are sufficient to build the
     * specified structure, without considering trades or swaps.
     *
     * @param structure:      The string representation of the structure to
     *                        be built.
     * @param resource_state: The available resources.
     * @return true iff the structure can be built with the available
     * resources, false otherwise.
     */
    public static boolean checkResources(String structure, int[] resource_state) {
        // FIXME: Task #7
        // cannot build K(Knight)
        char type = structure.charAt(0);
        int flag = 0;
        switch (type) {
            case 'R' -> {
                if ((resource_state[3] >= 1 && resource_state[4] >= 1))
                    flag++;
            }
            case 'S' -> {
                if (resource_state[4] >= 1 && resource_state[1] >= 1
                        && resource_state[2] >= 1 && resource_state[3] >= 1)
                    flag++;
            }
            case 'C' -> {
                if (resource_state[0] >= 3 && resource_state[1] >= 2)
                    flag++;
            }
            case 'J' -> {
                if (resource_state[0] >= 1 && resource_state[1] >= 1 && resource_state[2] >= 1)
                    flag++;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return flag == 1;
/*
        if (type == 'R') {
            if (resource_state[3] >= 1 && resource_state[4] >= 1) {
                flag++;
            }
        } else if (type == 'S') {
            if (resource_state[4] >= 1 && resource_state[1] >= 1 && resource_state[2] >= 1 && resource_state[3] >= 1) {
                flag++;
            }
        } else if (type == 'C') {
            if (resource_state[0] >= 3 && resource_state[1] >= 2) {
                flag++;
            }
        } else if (type == 'J') {
            if (resource_state[0] >= 1 && resource_state[1] >= 1 && resource_state[2] >= 1) {
                flag++;
            }
        }
        return flag == 1;
 */
    }

    /**
     * Check if the available resources are sufficient to build the
     * specified structure, considering also trades and/or swaps.
     * This method needs access to the current board state because the
     * board state encodes which Knights are available to perform swaps.
     *
     * @param structure:      The string representation of the structure to
     *                        be built.
     * @param board_state:    The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the structure can be built with the available
     * resources, false otherwise.
     */
    public static boolean checkResourcesWithTradeAndSwap(String structure,
                                                         String board_state,
                                                         int[] resource_state) {
        return false; // FIXME: Task #12
    }

    /**
     * Check if a player action (build, trade or swap) is executable in the
     * given board and resource state.
     *
     * @param action:         String representatiion of the action to check.
     * @param board_state:    The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the action is applicable, false otherwise.
     */
    public static boolean canDoAction(String action,
                                      String board_state,
                                      int[] resource_state) {
        return false; // FIXME: Task #9
    }

    /**
     * Check if the specified sequence of player actions is executable
     * from the given board and resource state.
     *
     * @param actions:        The sequence of (string representatins of) actions.
     * @param board_state:    The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the action sequence is executable, false otherwise.
     */
    public static boolean canDoSequence(String[] actions,
                                        String board_state,
                                        int[] resource_state) {
        return false; // FIXME: Task #11
    }

    /**
     * Find the path of roads that need to be built to reach a specified
     * (unbuilt) structure in the current board state. The roads should
     * be returned as an array of their string representation, in the
     * order in which they have to be built. The array should _not_ include
     * the target structure (even if it is a road). If the target structure
     * is reachable via the already built roads, the method should return
     * an empty array.
     * <p>
     * Note that on the Island One map, there is a unique path to every
     * structure.
     *
     * @param target_structure: The string representation of the structure
     *                          to reach.
     * @param board_state:      The string representation of the board state.
     * @return An array of string representations of the roads along the
     * path.
     */
    public static String[] pathTo(String target_structure,
                                  String board_state) {
        String[] result = {};
        return result; // FIXME: Task #13
    }

    /**
     * Generate a plan (sequence of player actions) to build the target
     * structure from the given board and resource state. The plan may
     * include trades and swaps, as well as bulding other structures if
     * needed to reach the target structure or to satisfy the build order
     * constraints.
     * <p>
     * However, the plan must not have redundant actions. This means it
     * must not build any other structure that is not necessary to meet
     * the building constraints for the target structure, and it must not
     * trade or swap for resources if those resources are not needed.
     * <p>
     * If there is no valid build plan for the target structure from the
     * specified state, return null.
     *
     * @param target_structure: The string representation of the structure
     *                          to be built.
     * @param board_state:      The string representation of the board state.
     * @param resource_state:   The available resources.
     * @return An array of string representations of player actions. If
     * there exists no valid build plan for the target structure,
     * the method should return null.
     */
    public static String[] buildPlan(String target_structure,
                                     String board_state,
                                     int[] resource_state) {
        return null; // FIXME: Task #14
    }

}
