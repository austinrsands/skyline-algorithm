package project;

import java.util.ArrayList;

public class Main {
    // Entry point
    public static void main(String[] args) {
        Building a = new Building(2, 10, 9);
        Building b = new Building(3, 15, 7);
        Building c = new Building(5, 12, 12);
        Building d = new Building(15, 10, 20);
        Building e = new Building(19, 8, 24);

        ArrayList<Building> buildings = new ArrayList<Building>();
        buildings.add(a);
        buildings.add(b);
        buildings.add(c);
        buildings.add(d);
        buildings.add(e);

        Skyline s = Skyline.generateSkyline(buildings, false);

        System.out.println(s);
    }
}