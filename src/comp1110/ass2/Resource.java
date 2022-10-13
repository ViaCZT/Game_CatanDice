package comp1110.ass2;

/**
 * The available resources that the dice roll will be added to:
 * 6 elements in total, each element is one of 0-5.
 * e.g. {0,1,2,3,4,5} represent {Ore, Grain, Wool, Timber, Bricks, Gold}
 *
 * @author Zetian Chen   uid: u7564812
 */
public class Resource {
    public int ore;
    public int grain;
    public int wool;
    public int timber;
    public int bricks;
    public int gold;

    /**
     * Constructor that make the resources match their quantities.
     *
     * @param resource_state: looks like [0,0,0,2,3,3]
     */
    public Resource(int[] resource_state) {
        this.ore = resource_state[0];
        this.grain = resource_state[1];
        this.wool = resource_state[2];
        this.timber = resource_state[3];
        this.bricks = resource_state[4];
        this.gold = resource_state[5];
    }
}
