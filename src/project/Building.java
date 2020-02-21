package project;

import java.util.ArrayList;

public class Building {
    private int left;
    private int height;
    private int right;

    /**
     * Constructs a new building
     * @param left the building's left position
     * @param height the building's height
     * @param right the building's right position
     */
    public Building(int left, int height, int right) {
        this.left = left;
        this.height = height;
        this.right = right;
    }

    /**
     * Gets the building's left position
     * @return the building's left position
     */
    public int getLeft() {
        return left;
    }

    /**
     * Gets the building's height
     * @return the building's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets the building's right position
     * @return the building's right position
     */
    public int getRight() {
        return right;
    }

    /**
     * Returns String representation of building
     */
    public String toString() {
        return "(" + left + ", " + height + ", " + right + ")";
    }

    /**
     * Converts and array of numbers to a list of buildings
     * @param nums a list of numbers corresponding to individual buildings (left, height, right, left, height, right,...)
     * @return
     */
    public static ArrayList<Building> arrayToBuildings(int[] nums) {
        // check if input is valid
        if (nums.length % 3 != 0) return null;
        // construct buildings
        ArrayList<Building> buildings = new ArrayList<Building>();
        for (int i = 0; i < nums.length - 2; i += 3) {
            buildings.add(new Building(nums[i], nums[i+1], nums[i+2]));
        }
        return buildings;
    }
}