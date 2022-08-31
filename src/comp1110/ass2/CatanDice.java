package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CatanDice {

    /**
     * Check if the string encoding of a board state is well-formed.
     * Note that this does not mean checking if the state is valid
     * (represents a state that the player could get to in game play),
     * only that the string representation is syntactically well-formed.
     *
     * @param board_state: The string representation of the board state.
     * @return true iff the string is a well-formed representation of
     *         a board state, false otherwise.
     */
    public static boolean isBoardStateWellFormed(String board_state) {
        String[] s = {"","R0","R1","R2","R3","R4","R5","R6","R7","R8","R9","R10","R11","R12","R13","R14","R15","S3","S4","S5","S7","S9","S11","C7","C12","C20","C30","J1","J2","J3","J4","J5","J6","K1","K2","K3","K4","K5","K6"};
        String[] bs = board_state.split(",");
        int t=0;
        for(int i=0;i<=bs.length-1;i++){
            if(Arrays.asList(s).contains(bs[i])){
                t++;
            }
        }
        return t == bs.length;


//        StringBuffer sb = new StringBuffer(board_state);
//        int i = 0;
//        if(board_state!=""){
//            if(board_state.length()==2){
//                sb.insert(1," ");
//                String s2 = new String(sb);
//                String[] s0 = s2.split(" ");
//                if(s0.length==2){
//                    char c =  s0[0].charAt(0);
//                    if(c=='R'){
//                        if(Integer.parseInt(s0[1])>=0&&Integer.parseInt(s0[1])<=15){
//                            i++ ;
//                        }
//                    }
//                    else if (c=='S'){
//                        if(Integer.parseInt(s0[1])==3||Integer.parseInt(s0[1])==4||Integer.parseInt(s0[1])==5||Integer.parseInt(s0[1])==7||Integer.parseInt(s0[1])==9||Integer.parseInt(s0[1])==11){
//                            i++;
//                        }
//                    }
//                    else if (c=='C'){
//                        if(Integer.parseInt(s0[1])==7||Integer.parseInt(s0[1])==12||Integer.parseInt(s0[1])==20||Integer.parseInt(s0[1])==30){
//                            i++;
//                        }
//                    }
//                    else if (c=='J'||c=='K'){
//                        if(Integer.parseInt(s0[1])>=1&&Integer.parseInt(s0[1])<=6){
//                            i++;
//                        }
//                    }
//                }
//            }
//
//
//        }
//        else i++;


//        if (i==1){
//            return true;
//        }
//        else return false;


	  // FIXME: Task #3
    }

    /**
     * Check if the string encoding of a player action is well-formed.
     *
     * @param action: The string representation of the action.
     * @return true iff the string is a well-formed representation of
     *         a board state, false otherwise.
     */
    public static boolean isActionWellFormed(String action) {
        String[] trade = {"1","2","3","4","0"};
        String[] swap = {"1","2","3","4","5","0"};
        String[] sp = action.split(" ");
        int i = 0;
        if(Objects.equals(sp[0], "build")){
            if (isBoardStateWellFormed(sp[1])){
                i++;
            }
        }
        else if(sp[0].equals("trade")&&sp.length==2){
            if (Arrays.asList(trade).contains(sp[1])){
                i++;
            }
        }
        else if(sp[0].equals("swap")&&sp.length==3){
            if(Arrays.asList(swap).contains(sp[1]) && Arrays.asList(swap).contains(sp[2])){
                i++;
            }
        }
        return i==1;
        // FIXME: Task #4
    }

    /**
     * Roll the specified number of dice and add the result to the
     * resource state.
     *
     * The resource state on input is not necessarily empty. This
     * method should only _add_ the outcome of the dice rolled to
     * the resource state, not remove or clear the resources already
     * represented in it.
     *
     * @param n_dice: The number of dice to roll (>= 0).
     * @param resource_state: The available resources that the dice
     *        roll will be added to.
     *
     * This method does not return any value. It should update the given
     * resource_state.
     */
    public static void rollDice(int n_dice, int[] resource_state) {
	// FIXME: Task #6
    }

    /**
     * Check if the specified structure can be built next, given the
     * current board state. This method should check that the build
     * meets the constraints described in section "Building Constraints"
     * of the README file.
     *
     * @param structure: The string representation of the structure to
     *        be built.
     * @param board_state: The string representation of the board state.
     * @return true iff the structure is a possible next build, false
     *         otherwise.
     */
    public static boolean checkBuildConstraints(String structure,
						String board_state) {
	 return false; // FIXME: Task #8
    }

    /**
     * Check if the available resources are sufficient to build the
     * specified structure, without considering trades or swaps.
     *
     * @param structure: The string representation of the structure to
     *        be built.
     * @param resource_state: The available resources.
     * @return true iff the structure can be built with the available
     *         resources, false otherwise.
     */
    public static boolean checkResources(String structure,
					 int[] resource_state) {
	 return false; // FIXME: Task #7
    }

    /**
     * Check if the available resources are sufficient to build the
     * specified structure, considering also trades and/or swaps.
     * This method needs access to the current board state because the
     * board state encodes which Knights are available to perform swaps.
     *
     * @param structure: The string representation of the structure to
     *        be built.
     * @param board_state: The string representation of the board state.
     * @param resource_state: The available resources.
     * @return true iff the structure can be built with the available
     *         resources, false otherwise.
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
     * @param action: String representatiion of the action to check.
     * @param board_state: The string representation of the board state.
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
     * @param actions: The sequence of (string representatins of) actions.
     * @param board_state: The string representation of the board state.
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
     * 
     * Note that on the Island One map, there is a unique path to every
     * structure. 
     *
     * @param target_structure: The string representation of the structure
     *        to reach.
     * @param board_state: The string representation of the board state.
     * @return An array of string representations of the roads along the
     *         path.
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
     *
     * However, the plan must not have redundant actions. This means it
     * must not build any other structure that is not necessary to meet
     * the building constraints for the target structure, and it must not
     * trade or swap for resources if those resources are not needed.
     *
     * If there is no valid build plan for the target structure from the
     * specified state, return null.
     *
     * @param target_structure: The string representation of the structure
     *        to be built.
     * @param board_state: The string representation of the board state.
     * @param resource_state: The available resources.
     * @return An array of string representations of player actions. If
     *         there exists no valid build plan for the target structure,
     *         the method should return null.
     */
    public static String[] buildPlan(String target_structure,
				     String board_state,
				     int[] resource_state) {
	 return null; // FIXME: Task #14
    }

}
