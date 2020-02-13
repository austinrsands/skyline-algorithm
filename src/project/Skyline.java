package project;

import java.util.ArrayList;

public class Skyline {

    // List of position and heights, (p, h, p, h,..., p, h)
    private ArrayList<Integer> skylineList;

    /**
     * Constructs empty skyline
     */
    public Skyline() {
        skylineList = new ArrayList<Integer>();
    }

    /**
     * Constructs skyline of single building
     */
    public Skyline(Building building) {
        this();
        // add starting position
        skylineList.add(building.getLeft());
        // add height
        skylineList.add(building.getHeight());
        // add ending position
        skylineList.add(building.getRight());
        // add height of zero to represent end of skyline
        skylineList.add(0);
    }

    /**
     * Constructs skyline of spike map
     * @param spikeMap a spikemap
     */
    public Skyline(SpikeMap spikeMap) {
        this();
        int start = spikeMap.getStart();
        ArrayList<Integer> spikes = spikeMap.getSpikes();
        int numSpikes = spikes.size();
        int currentSpike = -1;
        // loop through spikes, if there is a change in height (a.k.a new building) then update skline
        for (int i = 0; i < numSpikes; i++) {
            if (spikes.get(i) != currentSpike) {
                // if current spike is not the last spike, use spike height. Otherwise, use zero to represent end of skyline
                currentSpike = i < numSpikes - 1 ? spikes.get(i) : 0;
                skylineList.add(start + i);
                skylineList.add(currentSpike);
            }
            // if current spike is the last spike and the current height is not zero (end of skyline), then add the end of skyline
            if (currentSpike != 0 && i == numSpikes - 1) {
                skylineList.add(start + i);
                skylineList.add(0);
            }
        }
    }

    /**
     * Generates skyline from given buildings with given method
     * @param buildings the buildings to add to skyline
     * @param recursive whether to use recursive or inductive algorithm for generation
     * @return a skyline
     */
    public static Skyline generateSkyline(ArrayList<Building> buildings, boolean recursive) {
        if (recursive) {
            return divideAndConquerAlgorithm(buildings, 0, buildings.size() - 1);
        }
        else {
            return inductiveAlgorithm(buildings);
        }
    }

    /**
     * Creates skyline from list of buildings using a divide and conquer algorithms
     * @param buildings list of buildings to add to skyline
     * @param l left index of sub list
     * @param r right index of sub list
     * @return a skyline
     */
    public static Skyline divideAndConquerAlgorithm(ArrayList<Building> buildings, int l, int r) {
        // base case: essentially, if building sublist contains a single building, then return the skyline of the single building
        if (r <= l)
            return new Skyline(buildings.get(r));
        
        // calculate the middle index
        int mid = (l + r) / 2;

        // recurse on left half of sublist to obtain skyline
        Skyline left = divideAndConquerAlgorithm(buildings, l, mid);

        // recurse on right half of sublist to obtain skyline
        Skyline right = divideAndConquerAlgorithm(buildings, mid + 1, r);

        // merge the two skylines and return
        return merge(left, right);
    }

    /**
     * Creates skyline from list of buildings
     * @param buildings list of buildings to add to skyline
     * @return the resulting skyline
     */
    private static Skyline inductiveAlgorithm(ArrayList<Building> buildings) {
        Skyline skyline = new Skyline();
        // for each building, add it to the skyline
        for (Building building : buildings) {
            skyline = merge(skyline, new Skyline(building));
        }
        return skyline;
    }

    /**
     * Merges two skylines and returns result
     * @param a a skyline
     * @param b another skyline
     * @return the resulting skyline
     */
    public static Skyline merge(Skyline a, Skyline b) {
        // Converts skylines to spike maps and adds them
        SpikeMap spikeMapA = new SpikeMap(a);
        SpikeMap spikeMapB = new SpikeMap(b);
        return new Skyline(SpikeMap.add(spikeMapA, spikeMapB));
    }

    /**
     * Gets the list representation of skyline
     * @return the list representation of skyline
     */
    public ArrayList<Integer> getSkylineList() {
        return skylineList;
    }

    /**
     * Gets the string representation of skyline
     */
    public String toString() {
        return skylineList.toString().replace('[', '(').replace(']', ')');
    }
}