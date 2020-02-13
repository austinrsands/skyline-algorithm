package project;

import java.util.ArrayList;

public class Skyline {

    private ArrayList<Integer> skylineList;

    public Skyline() {
        skylineList = new ArrayList<Integer>();
    }

    public Skyline(Building building) {
        this();
        skylineList.add(building.getLeft());
        skylineList.add(building.getHeight());
        skylineList.add(building.getRight());
        skylineList.add(0);
    }

    public Skyline(SpikeMap spikeMap) {
        this();
        int start = spikeMap.getStart();
        ArrayList<Integer> spikes = spikeMap.getSpikes();
        int numSpikes = spikes.size();
        int currentSpike = -1;
        for (int i = 0; i < numSpikes; i++) {
            if (spikes.get(i) != currentSpike) {
                currentSpike = i < numSpikes - 1 ? spikes.get(i) : 0;
                skylineList.add(start + i);
                skylineList.add(currentSpike);
            }
            if (currentSpike != 0 && i == numSpikes - 1) {
                skylineList.add(start + i);
                skylineList.add(0);
            }
        }
    }

    public static Skyline generateSkyline(ArrayList<Building> buildings, boolean recursive) {
        Skyline skyline = new Skyline();
        if (recursive) {
            return divideAndConquerAlgorithm(buildings, 0, buildings.size() - 1);
        }
        else {
            return inductiveAlgorithm(skyline, buildings);
        }
    }

    public static Skyline divideAndConquerAlgorithm(ArrayList<Building> buildings, int l, int r) {
        if (r <= l)
            return new Skyline(buildings.get(r));
            
        int mid = (l + r) / 2;
        Skyline left = divideAndConquerAlgorithm(buildings, l, mid);
        Skyline right = divideAndConquerAlgorithm(buildings, mid + 1, r);
        return merge(left, right);
    }


    public static Skyline inductiveAlgorithm(Skyline skyline, ArrayList<Building> buildings) {
        for (Building building : buildings) {
            skyline = merge(skyline, new Skyline(building));
        }
        return skyline;
    }

    public static Skyline merge(Skyline a, Skyline b) {
        SpikeMap spikeMapA = new SpikeMap(a);
        SpikeMap spikeMapB = new SpikeMap(b);
        return new Skyline(SpikeMap.add(spikeMapA, spikeMapB));
    }

    public ArrayList<Integer> getSkylineList() {
        return skylineList;
    }

    public String toString() {
        return skylineList.toString().replace('[', '(').replace(']', ')');
    }
}