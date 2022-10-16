package comp1110.ass2;

/**
 * The board for each player.
 *
 * @author Zetian Chen   uid: u7564812
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


/*
    public List<String> getAllStructures() {
        String[] s = {"", "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15",
                "S3", "S4", "S5", "S7", "S9", "S11",
                "C7", "C12", "C20", "C30",
                "J1", "J2", "J3", "J4", "J5", "J6",
                "K1", "K2", "K3", "K4", "K5", "K6"};
        return Arrays.asList(s);
    }
*/
    public int point[] = {0 ,0, 0, 0, 0 ,0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public void getPoint(int turn){
        int all_point = 0;
        String[] state = getStructure();
        for(String str : state){
            switch (str.charAt(0)){
                case 'R' -> all_point++;//道路得分
                default -> {
                    int p = Integer.parseInt(str.substring(1));//房子 城市 骑士的得分
                    all_point+=p;
                }
            }
        }
        if(turn == 0){
            point[0] = all_point;
        }
        else
            point[turn] = all_point - point[turn-1];

        if(point[turn]==0){
            point[turn]=-2;
        }
    }
}
