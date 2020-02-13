package project;

import java.util.ArrayList;

public class SpikeMap {
    
    // start of spikes (index of first spike)
    private int start;

    // list of spike heights
    private ArrayList<Integer> spikes;

    /**
     * Constructs spike map from a starting position and spikes
     * @param start the starting position (index of first spike)
     * @param spikes the list of spike heights
     */
    public SpikeMap(int start, ArrayList<Integer> spikes) {
        this.start = start;
        this.spikes = spikes;
    }

    /**
     * Constructs a spike map from a skyline
     * @param skyline a skyline
     */
    public SpikeMap(Skyline skyline) {
        ArrayList<Integer> skylineList = skyline.getSkylineList();
        // check for minimum size
        if (skylineList.size() < 4) {
            start = 0;
            spikes = new ArrayList<Integer>();
            return;
        }
        // create empty spike list
        spikes = new ArrayList<Integer>();
        start = skylineList.get(0);
        // loop over starting positions
        for (int i = 0; i < skylineList.size() - 1; i += 2) {
            // if not the last building in skyline
            if (i < skylineList.size() - 2) {
                // for each part of building, add height to spike map
                for (int j = 0; j < skylineList.get(i + 2) - skylineList.get(i); j++) {
                    spikes.add(skylineList.get(i + 1));
                }
            // if last building in skyline
            } else {
                // add spike height of zero representing end of building
                spikes.add(0);
            }
        }
    }

    /**
     * Constructs a spike map from a building
     * @param building a building
     */
    public SpikeMap(Building building) {
        spikes = new ArrayList<Integer>();
        start = building.getLeft();
        // for each part of the building, add height to spike map
        for (int i = 0; i < building.getRight() - building.getLeft() + 1; i++) {
            spikes.add(building.getHeight());
        }
    }

    /**
     * Adds two spike maps together and returns result
     * @param a a spike map
     * @param b another spike map
     * @return the resulting spike map
     */
    public static SpikeMap add(SpikeMap a, SpikeMap b) {
        ArrayList<Integer> spikesA = a.getSpikes();
        ArrayList<Integer> spikesB = b.getSpikes();
        ArrayList<Integer> newSpikes = new ArrayList<Integer>();
        // calculate new starting position (index of first spike)
        int newStart = spikesA.size() > 0 ? Math.min(a.getStart(), b.getStart()) : b.getStart();
        // calculate new ending position (index of last spike)
        int newEnd = spikesA.size() > 0 ? Math.max(a.getStart() + spikesA.size() - 1, b.getStart() + spikesB.size() - 1) : b.getStart() + spikesB.size() - 1;
        // for each spike, add its height
        for (int i = 0; i < newEnd - newStart + 1; i++) {
            // calculate relative indices for each spike map
            int thisIndex = newStart - a.getStart() + i;
            int otherIndex = newStart - b.getStart() + i;
            int thisCurrentSpike = 0;
            int otherCurrentSpike = 0;
            // if index is within a, get the current spike
            if (thisIndex >= 0 && thisIndex < spikesA.size()) {
                thisCurrentSpike = spikesA.get(thisIndex);
            }
            // if index is within b, get the current spike
            if (otherIndex >= 0 && otherIndex < spikesB.size()) {
                otherCurrentSpike = spikesB.get(otherIndex);
            }
            // add the biggest of the two spikes to the spike map
            newSpikes.add(Math.max(thisCurrentSpike, otherCurrentSpike));
        }
        return new SpikeMap(newStart, newSpikes);
    }

    /**
     * Gets the starting position (index of first spike)
     * @return the starting position (index of first spike)
     */
    public int getStart() {
        return start;
    }

    /**
     * Gets the list of spike heights
     * @return the list of spike heights
     */
    public ArrayList<Integer> getSpikes() {
        return spikes;
    }

    /**
     * Gets the string representation of a spike map
     */
    public String toString() {
        return spikes.toString().replace('[', '(').replace(']', ')');
    }

    /**
     * Gets the diagram representation of the spike map
     * @return the diagram representation of the spike map
     */
    public String getDiagram() {
        String diagram = "";
        for (int i = 0; i < spikes.size(); i++) {
            for (int j = 0; j < spikes.get(i); j++) {
                diagram += "*";
            }
            diagram += "\n";
        }
        return diagram;
    }
}