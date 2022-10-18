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
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean isBoardStateWellFormed(String board_state) {
        // FIXME: Task #3
        if (board_state.equals(""))
            return true;
        Board myBoard = new Board(board_state);
        String[] structures = myBoard.getStructure();
        Structure myStruct = new Structure();
        int flag = 0;
        for (String structure : structures) {
            if (myStruct.getAllStructures().contains(structure)) {
                flag++;
            }
        }
        return flag == structures.length;
    }

    /**
     * Check if the string encoding of a player action is well-formed.
     *
     * @param action: The string representation of the action.
     * @return true iff the string is a well-formed representation of
     * a board state, false otherwise.
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean isActionWellFormed(String action) {
        // FIXME: Task #4
        Player myPlayer = new Player();
        String actionType = myPlayer.getActionType(action);
        String[] actionSplit = action.split(" ");
        if (actionType.equals("build")) {
            return isBoardStateWellFormed(actionSplit[1]);
        } else if (actionType.equals("trade") && actionSplit.length == 2) {
            return myPlayer.getTradeResource().contains(actionSplit[1]);
        } else if (actionType.equals("swap") && actionSplit.length == 3) {
            return myPlayer.getSwapResource().contains(actionSplit[1]) &&
                    myPlayer.getSwapResource().contains(actionSplit[2]);
        }
        return false;
    }

    public static int[] diceState(int n_dice) {
        int[] dices = new int[n_dice];
        Random ran = new Random();
        for (int i = 0; i <= n_dice - 1; i++) {
            int resource_index = ran.nextInt(6);
            dices[i] = resource_index;
        }
        return dices;
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
     * @param n_dice:        The number of dice to roll (>= 0).
     * @param resource_state The available resources that the dice
     *                       roll will be added to.
     *                       <p>
     *                       This method does not return any value. It should update the given
     *                       resource_state.
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static void rollDice(int n_dice, int[] resource_state) {
        // FIXME: Task #6
        int[] dices = diceState(n_dice);
        for (int i = 0; i <= n_dice - 1; i++) {
            resource_state[dices[i]]++;
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
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean checkBuildConstraints(String structure, String board_state) {
        // FIXME: Task #8
        List<String> myBoardState = Arrays.asList(new Board(board_state).getStructure());
        Structure myStructure = new Structure();
        List<String> rList = myStructure.getAllRoads();
        List<String> sList = myStructure.getAllSettles();
        List<String> cList = myStructure.getAllCities();
        List<String> jList = myStructure.getAllJokers();
        List<String> kList = myStructure.getAllKnights();

        if (rList.contains(structure)) {
            switch (structure) {
                case "R0" -> {
                    return true;
                }
                case "R2" -> {
                    if (myBoardState.contains("R0")) {
                        return true;
                    }
                }
                case "R5" -> {
                    if (myBoardState.contains("R3")) {
                        return true;
                    }
                }
                case "R12" -> {
                    if (myBoardState.contains("R7")) {
                        return true;
                    }
                }
                default -> {
                    if (myBoardState.contains(rList.get(rList.indexOf(structure) - 1))) {
                        return true;
                    }
                }
            }
        } else if (sList.contains(structure)) {
            if (!structure.equals("S3")) {
                return myBoardState.contains(sList.get(sList.indexOf(structure) - 1)) && myBoardState.contains(getRoad(structure));
            } else {
//                return myBoardState.contains(getRoad(structure));
                return true;
            }
        } else if (cList.contains(structure)) {
            if (!structure.equals("C7")) {
                return myBoardState.contains(cList.get(cList.indexOf(structure) - 1)) && myBoardState.contains(getRoad(structure));
            } else {
                return myBoardState.contains(getRoad(structure));
//                return true;
            }
        } else if (jList.contains(structure)) {
            if (structure.equals("J1")) {
                return true;
            } else {
                return myBoardState.contains(jList.get(jList.indexOf(structure) - 1)) || myBoardState.contains(kList.get(jList.indexOf(structure) - 1));
            }
        }
        return false;
    }

    /**
     * Find the road which connect the target structure
     *
     * @param structure target structure
     * @return The road near the structure
     * @author Zihan Ai (uid: u7528678)
     */
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
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean checkResources(String structure, int[] resource_state) {
        // FIXME: Task #7
        char type = structure.charAt(0);
        Resource myResource = new Resource(resource_state);
        switch (type) {
            case 'R' -> {
                if ((myResource.timber >= 1 && myResource.bricks >= 1))
                    return true;
            }
            case 'S' -> {
                if (myResource.bricks >= 1 && myResource.timber >= 1
                        && myResource.wool >= 1 && myResource.grain >= 1)
                    return true;
            }
            case 'C' -> {
                if (myResource.ore >= 3 && myResource.grain >= 2)
                    return true;
            }
            case 'J' -> {
                if (myResource.ore >= 1 && myResource.wool >= 1 && myResource.grain >= 1)
                    return true;
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return false;
    }


    /**
     * Automatic change the resources to satisfy the actions
     * use for checkResourcesWithTradeAndSwap and buildPlan
     *
     * @param resource_state: The available resources.
     * @param board_state     :The current board state
     * @return The action to change.
     * @author Zihan Ai (uid: u7528678)
     */
    public static String[] autoChangeResource(String board_state, int[] resource_state) {
        List<String> act = new ArrayList<>();
        int[] new_resource_state = new int[6];
        System.arraycopy(resource_state, 0, new_resource_state, 0, 6);
        int count = 0;
        for (int a : new_resource_state) {
            if (a < 0)
                count++;
        }

        for (int i = 0; i <= 5; i++) {
            if (new_resource_state[i] < 0) {
                if (count >= 2 && new_resource_state[5] < 4) {
                    board_state = useSwap(board_state, act, new_resource_state, i);
                }
                // If it can trade, use trade first
                if (new_resource_state[5] >= 2) { //Check if trade can replenish the resource
                    updateResourceState("trade " + i, new_resource_state);
                    act.add("trade " + i);
                    if (new_resource_state[i] == 0) {
                        continue;
                    }
                }


                board_state = useSwap(board_state, act, new_resource_state, i);

                if (new_resource_state[i] < 0) {
                    if (canDoAction("build J" + (i + 1), board_state, resource_state)) {
                        act.add("build J" + (i + 1));
                        board_state = updateBoardState("build J" + (i + 1), board_state);
                        updateResourceState("build J" + (i + 1), new_resource_state);
                        i--;
                    }
                }
            }
        }
        for (int i = 0; i <= 5; i++) {
            if (new_resource_state[i] < 0) {
                return null;
            }
        }
        String[] actions = new String[act.size()];
        for (int i = 0; i <= act.size() - 1; i++) {
            actions[i] = act.get(i);
        }
        return actions;
    }

    public static String useSwap(String board_state, List<String> act, int[] new_resource_state, int i) {
        for (int j = 0; j <= 5; j++) {
            if (checkCanSwap(board_state, new_resource_state, j, i)) { //Check if swap can replenish the resource
                updateResourceState("swap " + j + " " + i, new_resource_state);
                board_state = updateBoardState("swap " + j + " " + i, board_state);
                act.add("swap " + j + " " + i);
            }
        }
        return board_state;
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
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean checkResourcesWithTradeAndSwap(String structure,
                                                         String board_state,
                                                         int[] resource_state) {
        System.out.println("\n");
        int[] new_resource_state = new int[6];
        String[] actions = null;
        System.arraycopy(resource_state, 0, new_resource_state, 0, 6);
        if (checkBuildConstraints(structure, board_state)) {
            if (!checkResources(structure, new_resource_state)) {
                updateResourceState("build " + structure, new_resource_state);//Lets minus the resources first, which resource amount less than 0 is the target resource.
                actions = autoChangeResource(board_state, new_resource_state);
            }
        } else
            return true;
        return actions != null;
        // FIXME: Task #12
    }

    /**
     * Check if a swap is executable in the given board and resource state.
     * A help method for Task 9 canDoAction().
     *
     * @param board_state:     The string representation of the board state.
     * @param resource_state:  The available resources.
     * @param swap_resource:   The resource used to swap.
     * @param target_resource: The resource wanted to get (must have built this Joker).
     * @return: True if the swap is executable, false if it is not.
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean checkCanSwap(String board_state, int[] resource_state,
                                       int swap_resource, int target_resource) {
        String[] states = board_state.split(",");
        List<String> stateList = new ArrayList<>();
        Collections.addAll(stateList, states);
        // must 1.have built the Joker of target resource && 2.have enough resource to swap.
        String joker = "J" + (target_resource + 1);
        return (stateList.contains(joker) || stateList.contains("J6")) && resource_state[swap_resource] >= 1;
    }

    /**
     * Check if a player action (build, trade or swap) is executable in the
     * given board and resource state.
     *
     * @param action:         String representation of the action to check.
     * @param board_state:    The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the action is applicable, false otherwise.
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean canDoAction(String action,
                                      String board_state,
                                      int[] resource_state) {
        // FIXME: Task #9
        if (isActionWellFormed(action)) {
            String behaviour = action.split(" ")[0]; // is "build", "trade", or "swap"
            switch (behaviour) {
                case "build" -> {
                    String structure = action.split(" ")[1]; //what to be built
                    if (checkBuildConstraints(structure, board_state)) {
                        if (checkResources(structure, resource_state)) {
                            return true;
                        }
                    }
                }
                case "trade" -> {
                    if (resource_state[5] >= 2) {
                        return true;
                    }
                }
                case "swap" -> {
                    int swap_resource = Integer.parseInt(action.split(" ")[1]);
                    int target_resource = Integer.parseInt(action.split(" ")[2]);
                    if (checkCanSwap(board_state, resource_state, swap_resource, target_resource)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * When execute an action
     * change the board state
     *
     * @param action      the action to execute
     * @param board_state the current board state
     * @return the new board state
     * @auther Zihan Ai (uid: u7528678)
     */
    public static String updateBoardState(String action, String board_state) {
        String behaviour = action.split(" ")[0];
        switch (behaviour) {
            case "build" -> board_state += ("," + action.split(" ")[1]);
            case "swap" -> {
                String[] structures = board_state.split(",");
                int resource_index = Integer.parseInt(action.split(" ")[2]);
                String joker = "J" + (resource_index + 1);
                boolean no_specific_joker = true;
                boolean have_J6 = false;
                for (int i = 0; i < structures.length; i++) {
                    if (structures[i].equals(joker)) {
                        structures[i] = "K" + (resource_index + 1);
                        no_specific_joker = false;
                        break;
                    } else if (structures[i].equals("J6")) {
                        have_J6 = true;
                    }
                }
                for (int i = 0; i < structures.length; i++) {
                    if (structures[i].equals("J6") && no_specific_joker && have_J6) {
                        structures[i] = "K6";
                    }
                }
                StringBuilder board_stateBuilder = new StringBuilder(structures[0]);
                for (int i = 1; i < structures.length; i++) {
                    board_stateBuilder.append(",").append(structures[i]);
                }
                board_state = board_stateBuilder.toString();
            }
        }
        return board_state;
    }

    /**
     * Change the quantity of resources after action
     *
     * @param action         the action will execute
     * @param resource_state the current resource state
     * @return the new resource state
     * @auther Zihan Ai (uid: u7528678),
     */
    public static int[] updateResourceState(String action, int[] resource_state) {
        String behaviour = action.split(" ")[0];
        switch (behaviour) {
            case "build" -> {
                char structure = action.split(" ")[1].charAt(0);
                switch (structure) {
                    case 'R' -> {
                        resource_state[3] -= 1;
                        resource_state[4] -= 1;
                    }
                    case 'S' -> {
                        resource_state[1] -= 1;
                        resource_state[2] -= 1;
                        resource_state[3] -= 1;
                        resource_state[4] -= 1;
                    }
                    case 'C' -> {
                        resource_state[0] -= 3;
                        resource_state[1] -= 2;
                    }
                    case 'J' -> {
                        resource_state[0] -= 1;
                        resource_state[1] -= 1;
                        resource_state[2] -= 1;
                    }
                }
            }
            case "trade" -> {
                int target_resource = Integer.parseInt(action.split(" ")[1]);
                resource_state[5] -= 2;
                resource_state[target_resource]++;
            }
            case "swap" -> {
                int swap_resource = Integer.parseInt(action.split(" ")[1]);
                int target_resource = Integer.parseInt(action.split(" ")[2]);
                resource_state[swap_resource] -= 1;
                resource_state[target_resource] += 1;
            }
        }
        return resource_state;
    }

    /**
     * Check if the specified sequence of player actions is executable
     * from the given board and resource state.
     *
     * @param actions:        The sequence of (string representations of) actions.
     * @param board_state:    The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the action sequence is executable, false otherwise.
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static boolean canDoSequence(String[] actions,
                                        String board_state,
                                        int[] resource_state) {
        int[] new_resource_state = new int[6];
        System.arraycopy(resource_state, 0, new_resource_state, 0, 6);
        int count = 0;
        for (String action : actions) {
            if (canDoAction(action, board_state, new_resource_state)) {
                board_state = updateBoardState(action, board_state);
                updateResourceState(action, new_resource_state);
                count++;
            } else
                break;
        }
        return count == actions.length; // FIXME: Task #11
    }

    public static String[] settlementsOrder = {"S3", "R0", "R2", "S4", "R3", "R5", "S5", "R6", "R7", "S7", "R8", "R9", "S9", "R10", "R11", "S11"};
    public static String[] C7Path = {"R0", "R1"};
    public static String[] C12Path = {"R0", "R2", "R3", "R4"};
    public static String[] C20Path = {"R0", "R2", "R3", "R5", "R6", "R7", "R12", "R13"};
    public static String[] C30Path = {"R0", "R2", "R3", "R5", "R6", "R7", "R12", "R13", "R14", "R15"};
    public static String[] mainRoad = {"R0", "R2", "R3", "R5", "R6", "R7", "R8", "R9", "R10", "R11"};

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
     * @return An array of string representations of the roads along the path
     * @author Zetian Chen (uid: u7564812), Zihan Ai (uid: u7528678)
     */
    public static String[] pathTo(String target_structure,
                                  String board_state) {
        List<String> path = new ArrayList<>();
        List<String> board = new ArrayList<>();
        String[] board0 = board_state.split(",");
        if (board_state.length() != 0) {
            for (int i = 0; i <= board0.length - 1; i++) {
                if (board0[i].charAt(0) == 'R') {
                    board.add(board0[i]);
                }
            }
        }
        char type = target_structure.charAt(0);
        switch (type) {
            case 'S' -> {
                List<String> settlementPath = new ArrayList<>();
                for (int n = 0; n <= 15; n++) {
                    if (!settlementsOrder[n].equals(target_structure) && settlementsOrder[n].charAt(0) == 'R') {
                        settlementPath.add(settlementsOrder[n]);
                    } else if (settlementsOrder[n].equals(target_structure)) {
                        break;
                    }
                }
                for (int s = 0; s <= settlementPath.size() - 1; s++) {
                    if (!board.contains(settlementPath.get(s))) {
                        path.add(settlementPath.get(s));
                    }
                }
            }
            case 'C' -> {
                List<String> cityPath = new ArrayList<>();
                switch (target_structure) {
                    case "C7" -> Collections.addAll(cityPath, C7Path);
                    case "C12" -> Collections.addAll(cityPath, C12Path);
                    case "C20" -> Collections.addAll(cityPath, C20Path);
                    case "C30" -> Collections.addAll(cityPath, C30Path);
                }
                for (int s = 0; s <= cityPath.size() - 1; s++) {
                    if (!board.contains(cityPath.get(s))) {
                        path.add(cityPath.get(s));
                    }
                }
            }
            case 'R' -> {
                List<String> roadPath = new ArrayList<>();
                switch (target_structure) {
                    case "R1" -> Collections.addAll(roadPath, C7Path);
                    case "R4" -> Collections.addAll(roadPath, C12Path);
                    case "R12", "R13", "R14", "R15" -> {
                        for (int i = 0; i <= 10; i++) {
                            if (!C30Path[i].equals(target_structure)) {
                                roadPath.add(C30Path[i]);
                            } else
                                break;
                        }
                    }
                    default -> {
                        for (int i = 0; i <= 10; i++) {
                            if (!mainRoad[i].equals(target_structure)) {
                                roadPath.add(mainRoad[i]);
                            } else
                                break;
                        }
                    }
                }
                for (int s = 0; s <= roadPath.size() - 1; s++) {
                    if (!board.contains(roadPath.get(s)) && !roadPath.get(s).equals(target_structure)) {
                        path.add(roadPath.get(s));
                    }
                }
            }
        }

        String[] result = new String[path.size()];
        for (int i0 = 0; i0 <= path.size() - 1; i0++) {
            result[i0] = path.get(i0);
        }
        return result; // FIXME: Task #13
    }

    /**
     * Generate a plan (sequence of player actions) to build the target
     * structure from the given board and resource state. The plan may
     * include trades and swaps, as well as building other structures if
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
     * @auther Zihan Ai (uid: u7528678)
     */
    public static String[] buildPlan(String target_structure,
                                     String board_state,
                                     int[] resource_state) {
        //FIXME: task #14
        Structure myStructure = new Structure();
        List<String> sList = myStructure.getAllSettles();
        List<String> cList = myStructure.getAllCities();
        List<String> jList = myStructure.getAllJokers();
        List<String> kList = myStructure.getAllKnights();
        String[] st = board_state.split(",");
        List<String> board = new ArrayList<>(Arrays.asList(st));
        List<String> build = new ArrayList<>();
        List<String> p = new ArrayList<>();

        if (target_structure.charAt(0) == 'S' && !target_structure.equals("S3")) {
            if (!board.contains(sList.get(sList.indexOf(target_structure) - 1))) {
                return null;
            }
        }
        if (target_structure.charAt(0) == 'C' && !target_structure.equals("C7")) {
            if (!board.contains(cList.get(cList.indexOf(target_structure) - 1))) {
                return null;
            }
        }
        if (target_structure.charAt(0) == 'J' && !target_structure.equals("J1")) {
            if (!board.contains(jList.get(jList.indexOf(target_structure) - 1)) && !board.contains(kList.get(jList.indexOf(target_structure) - 1))) {
                build.add("build " + jList.get(jList.indexOf(target_structure) - 1));
            }
        }

        int[] new_resource_state = new int[6];
        String[] path = pathTo(target_structure, board_state);
        for (String road : path) {
            build.add("build " + road);
        }
        build.add("build " + target_structure);
        System.arraycopy(resource_state, 0, new_resource_state, 0, 6);
        for (String action : build) {
            if (canDoAction(action, board_state, new_resource_state)) {
                new_resource_state = updateResourceState(action, resource_state);
                board_state = updateBoardState(action, board_state);
                p.add(action);
            } else if (checkResourcesWithTradeAndSwap(action.split(" ")[1], board_state, new_resource_state)) {
//                System.out.println("1");
                updateResourceState(action, new_resource_state);
                String[] s = autoChangeResource(board_state, new_resource_state);
                if (s == null)
                    return null;

                p.addAll(Arrays.asList(s));
                p.add(action);
            } else
                return null;
        }
        String[] plan = new String[p.size()];
        for (int i = 0; i <= p.size() - 1; i++) {
            plan[i] = p.get(i);
            System.out.println(plan[i]);
        }
        return plan;
    }

}
