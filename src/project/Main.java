package project;

public class Main {
    // Entry point
    public static void main(String[] args) {

        int[] sky1 = {30,12,35,1,9,4,60,11,65,52,17,54,97,20,99,63,9,70,32,17,33,20,18,26,13,9,23,3,6,12,6,7,10,25,6,29,34,7,42,38,8,45,72,6,77,75,7,80,85,8,86,28,9,33,40,10,45,80,9,83,65,13,68,40,3,47,55,8,61,95,12,100,40,12,43,82,4,87,90,3,98,45,6,51,86,5,97,49,10,57,47,5,53,14,2,17};
        int[] sky2 = {30,11,35,10,7,14,55,4,58,16,7,20,1,1,4,36,8,39,22,6,25,56,2,60,6,10,7,17,8,18,45,7,49,33,13,35,23,11,26,46,8,48,3,9,8,40,1,60,27,9,32,2,3,6,11,8,16,37,10,38,8,4,40};
        int[] sky3 = {6,6,27,11,11,22,16,26,17,3,3,30,9,9,24,13,14,20,5,5,28,15,21,18,7,7,26,10,10,23,4,4,29,8,8,25,1,1,32,14,17,19,2,2,31,12,12,21};

        // Sky 1
        System.out.printf("Sky 1 Skyline Recursive (position, height, position, height,...): \n%s\n\n", Skyline.generateSkyline(Building.arrayToBuildings(sky1), true));
        System.out.printf("Sky 1 Skyline Inductive (position, height, position, height,...): \n%s\n\n\n", Skyline.generateSkyline(Building.arrayToBuildings(sky1), false));

        // Sky 2
        System.out.printf("Sky 2 Skyline Recursive (position, height, position, height,...): \n%s\n\n", Skyline.generateSkyline(Building.arrayToBuildings(sky2), true));
        System.out.printf("Sky 2 Skyline Inductive (position, height, position, height,...): \n%s\n\n\n", Skyline.generateSkyline(Building.arrayToBuildings(sky2), false));

        // Sky 3
        System.out.printf("Sky 3 Skyline Recursive (position, height, position, height,...): \n%s\n\n", Skyline.generateSkyline(Building.arrayToBuildings(sky3), true));
        System.out.printf("Sky 3 Skyline Inductive (position, height, position, height,...): \n%s\n\n\n", Skyline.generateSkyline(Building.arrayToBuildings(sky3), false));

    }
}