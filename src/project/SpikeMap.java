package project;

import java.util.ArrayList;

public class SpikeMap {
    private int start;
    private ArrayList<Integer> spikes;

    public SpikeMap(int start, ArrayList<Integer> spikes) {
        this.start = start;
        this.spikes = spikes;
    }

    public SpikeMap(Skyline skyline) {
        ArrayList<Integer> skylineList = skyline.getSkylineList();
        if (skylineList.size() < 4) {
            start = 0;
            spikes = new ArrayList<Integer>();
            return;
        }
        spikes = new ArrayList<Integer>();
        start = skylineList.get(0);
        for (int i = 0; i < skylineList.size() - 1; i += 2) {
            if (i < skylineList.size() - 2) {
                for (int j = 0; j < skylineList.get(i + 2) - skylineList.get(i); j++) {
                    spikes.add(skylineList.get(i + 1));
                }
            } else {
                spikes.add(0);
            }
        }
    }

    public SpikeMap(Building building) {
        spikes = new ArrayList<Integer>();
        start = building.getLeft();
        for (int i = 0; i < building.getRight() - building.getLeft() + 1; i++) {
            spikes.add(building.getHeight());
        }
    }

    public static SpikeMap add(SpikeMap a, SpikeMap b) {
        ArrayList<Integer> spikesA = a.getSpikes();
        ArrayList<Integer> spikesB = b.getSpikes();
        ArrayList<Integer> newSpikes = new ArrayList<Integer>();
        int newStart = spikesA.size() > 0 ? Math.min(a.getStart(), b.getStart()) : b.getStart();
        int newEnd = spikesA.size() > 0 ? Math.max(a.getStart() + spikesA.size() - 1, b.getStart() + spikesB.size() - 1) : b.getStart() + spikesB.size() - 1;
        for (int i = 0; i < newEnd - newStart + 1; i++) {
            int thisIndex = newStart - a.getStart() + i;
            int otherIndex = newStart - b.getStart() + i;
            int thisCurrentSpike = 0;
            int otherCurrentSpike = 0;
            if (thisIndex >= 0 && thisIndex < spikesA.size()) {
                thisCurrentSpike = spikesA.get(thisIndex);
            }
            if (otherIndex >= 0 && otherIndex < spikesB.size()) {
                otherCurrentSpike = spikesB.get(otherIndex);
            }
            newSpikes.add(Math.max(thisCurrentSpike, otherCurrentSpike));
        }
        return new SpikeMap(newStart, newSpikes);
    }

    public int getStart() {
        return start;
    }

    public ArrayList<Integer> getSpikes() {
        return spikes;
    }

    public String toString() {
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