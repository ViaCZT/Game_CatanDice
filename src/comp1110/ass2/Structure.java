package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * record all the structures in the game.
 * There are 5 categories: Roads, Settlements(Settles), Cities, Jokers, Knights.
 *
 * @author Zetian Chen   uid: u7564812
 */
public class Structure {
    public Structure() {
    }

    public List<String> getAllRoads() {
        String[] Roads = {"R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "R10", "R11", "R12", "R13", "R14", "R15"};
        return Arrays.asList(Roads);
    }

    public List<String> getAllSettles() {
        String[] Settlements = {"S3", "S4", "S5", "S7", "S9", "S11"};
        return Arrays.asList(Settlements);
    }

    public List<String> getAllCities() {
        String[] Cities = {"C7", "C12", "C20", "C30"};
        return Arrays.asList(Cities);
    }

    public List<String> getAllJokers() {
        String[] Jokers = {"J1", "J2", "J3", "J4", "J5", "J6"};
        return Arrays.asList(Jokers);
    }

    public List<String> getAllKnights() {
        String[] Knights = {"K1", "K2", "K3", "K4", "K5", "K6"};
        return Arrays.asList(Knights);
    }

    public List<String> getAllStructures() {
        List<String> allStructures = new ArrayList<>();
        allStructures.addAll(getAllRoads());
        allStructures.addAll(getAllCities());
        allStructures.addAll(getAllSettles());
        allStructures.addAll(getAllJokers());
        allStructures.addAll(getAllKnights());
        return allStructures;
    }
}
