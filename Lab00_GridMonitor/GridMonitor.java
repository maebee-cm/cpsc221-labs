import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GridMonitor implements GridMonitorInterface {
    private int height;
    private int width;
    private double[][] grid;
    private double[][] surroundingSumGrid;
    private double[][] surroundingAvgGrid;
    private double[][] deltaGrid;
    private boolean[][] dangerGrid;

    // Constructs a new GridMonitor from the given filePath. This automatically
    // Calculates all the grids necessary for monitoring the solar cell array.
    public GridMonitor(String filename) throws FileNotFoundException {
        // Had to add `"SampleFiles/"+`. The GridMonitorTest class only passes
        // file names rather than passing a real path to them. This is left
        // undocumented in the project specification as far as I can tell.
        File file = new File("SampleFiles/"+filename);
        // File could not exist
        try {
            Scanner scanner = new Scanner(file);

            // File should specify how many rows there are first, then how many columns.
            height = scanner.nextInt();
            width = scanner.nextInt();

            grid = new double[height][width];

            // Knowing the size of the grid now we just read this into our matrix.
            for(int i = 0; i < height; i++) {
                for(int j = 0; j < width; j++) {
                    grid[i][j] = scanner.nextDouble();
                }
            }

            System.out.println(grid);

            scanner.close();
        } catch (FileNotFoundException e) {
            throw e;
        }

        // Calculates all grids specified by the project instructions.
        surroundingSumGrid = calculateSurroundingSumGrid();
        surroundingAvgGrid = calculateSurroundingAvgGrid();
        deltaGrid = calculateDeltaGrid();
        dangerGrid = calculateDangerGrid();
    }

    private double[][] calculateSurroundingSumGrid() {
        double[][] surroundingSumGrid = new double[height][width];

        // Iterate through each element in our original grid, and find the sum
        // of all numbers above, below, to the left, or right of it. Replacing any
        // that don't exist due to the number being at a border with the number itself.
        // Then assign that sum to the position that corresponds to that cell in
        // surroundingSumGrid
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                double above, below, left, right;

                // Check if we're at the top row
                if(i == 0) {
                    above = grid[i][j];
                }
                else {
                    above = grid[i-1][j];
                }

                // Check if we're at the bottom row
                if(i == height-1) {
                    below = grid[i][j];
                }
                else {
                    below = grid[i+1][j];
                }

                // Check if we're at the left side
                if(j == 0) {
                    left = grid[i][j];
                }
                else {
                    left = grid[i][j-1];
                }

                // Check if we're at the right side
                if(j == width-1) {
                    right = grid[i][j];
                }
                else {
                    right = grid[i][j+1];
                }

                surroundingSumGrid[i][j] = above + below + left + right;
            }
        }

        return surroundingSumGrid;
    }

    // Calculates the average value of each cell's surrounding sums by iterating
    // over surroundSumGrid and dividing each cell's value by 4
    private double[][] calculateSurroundingAvgGrid() {
        double[][] surroundingAvgGrid = new double[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                surroundingAvgGrid[i][j] = surroundingSumGrid[i][j]/4;
            }
        }

        return surroundingAvgGrid;
    }

    // Calculates the delta that specifies the safe range of operation for each
    // cell in the grid array. This is calculated by dividing the average value
    // of each cell's sum of neighboring cells by 2
    private double[][] calculateDeltaGrid() {
        double[][] deltaGrid = new double[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                deltaGrid[i][j] = Math.abs(surroundingAvgGrid[i][j]/2);
            }
        }

        return deltaGrid;
    }

    // Calculates which cells exceede the safe range of operation specified by the
    // deltaGrid.
    private boolean[][] calculateDangerGrid() {
        boolean[][] dangerGrid = new boolean[height][width];

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                double cellValue = grid[i][j];
                double max = surroundingAvgGrid[i][j]+deltaGrid[i][j];
                double min = surroundingAvgGrid[i][j]-deltaGrid[i][j];

                if(cellValue > min && cellValue < max) {
                    dangerGrid[i][j] = false;
                }
                else {
                    dangerGrid[i][j] = true;
                }
            }
        }

        return dangerGrid;
    }

    // Creates a deep copy of a given 2D double matrix, by copying
    // each row one by one.
    private static double[][] deepCopy(double[][] original) {
        double[][] copy = new double[original.length][];

        for(int i = 0; i < original.length; i++) {
            copy[i] = Arrays.copyOf(original[i], original[i].length);
        }

        return copy;
    }

    // Return a copy of the original grid
    @Override
    public double[][] getBaseGrid() {
        return deepCopy(grid);
    }

    // Return a copy of the surrounding sum grid
    @Override
    public double[][] getSurroundingSumGrid() {
        return deepCopy(surroundingSumGrid);
    }

    // Return a copy of the average sum grid
    @Override
    public double[][] getSurroundingAvgGrid() {
        return deepCopy(surroundingAvgGrid);
    }

    // Return a copy of the delta grid
    @Override
    public double[][] getDeltaGrid() {
        return deepCopy(deltaGrid);
    }

    // Return a copy of the danger grid. Didn't get its own function cause its
    // the only one that has booleans, and I didn't want to bother with generics
    @Override
    public boolean[][] getDangerGrid() {
        boolean[][] copy = new boolean[dangerGrid.length][];

        for(int i = 0; i < dangerGrid.length; i++) {
            copy[i] = Arrays.copyOf(dangerGrid[i], dangerGrid[i].length);
        }

        return copy;
    }

    // There is a test for this, it is not mentioned in any of the lab instructions
    // as far as I can tell, so what this does is simply guess work on my end. This
    // will print the original grid, then prints the danger grid. I can only imagine
    // That in this hypothetical scenario these are the only things that would matter.
    public String toString() {
        String str = new String();

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                str += grid[i][j];
                str += " ";
            }

            str += "\n";
        }

        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                str += dangerGrid[i][j];
                str += " ";
            }

            str += "\n";
        }

        return str;
    }
}
