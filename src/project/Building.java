package project;

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
}