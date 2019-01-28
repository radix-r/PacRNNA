import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;
import pacsim.*;

/**
 *
 * University of Central Florida
 * CAP4630 - Spring 2019
 * Authors: Bailey Brooks and Ross Wagner
 *
 **/


public class PacSimRNNA implements PacAction {

    private List<Point> path;
    private int simTime;
    private List<PotentialSolution> potentialSolutions;
    private List<Point> food; // he entries in this array should be ordered according to increasing values of the x-value, and for each value of x, according to increasing values of the y-value

    public PacSimRNNA( String fname ) {
        PacSim sim = new PacSim( fname );
        sim.init(this);
    }

    public static void main( String[] args ) {
        System.out.println("\nTSP using RNNA agent by Bailey Brooks and Ross Wagner:");
        System.out.println("\nMaze : " + args[ 0 ] + "\n" );
        new PacSimRNNA( args[ 0 ] );
    }

    @Override
    public void init() {
        simTime = 0;
        path = new ArrayList();
        potentialSolutions = new ArrayList<PotentialSolution>();

    }



    @Override
    public PacFace action( Object state ){

        PacCell[][] grid = (PacCell[][]) state;
        PacmanCell pc = PacUtils.findPacman( grid );

        // make sure Pac-Man is in this game
        if( pc == null ) return null;

        // calculate only if list is empty (once)
        if( path.isEmpty() ) {

            // food array
            food = getAllFood(grid);

            // calc cost table
            int[][] costTable = generateCostTable(grid, pc);

            // calc the stuff bro
            PotentialSolution ps = new PotentialSolution();
        }

        Collections.sort(potentialSolutions);

        PacFace face = null;
        return face;
    }

    private int[][] makeCostTable(PacCell[][] B, PacmanCell pc){

        // Find each food pellet throughout board
        List<Point> foodPels = PacUtils.findFood(B);

        // Create Cost Table
        int size = foodPels.size() + 1;
        int[][] costTable = new int[size][size];

        for (int i = 1; i < size; i++){
            int cost = BFSPath.getPath(B, pc.getLoc(), foodPels.get(i - 1)).size();
            costTable[0][i] = cost;
            costTable[i][0] = cost;
        }

        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                costTable[i][j] = BFSPath.getPath(B, foodPels.get(i - 1), foodPels.get(j - 1)).size();
            }
        }

        // Print cost table
        System.out.println("Cost table:");
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                System.out.println("%-3d", costTable[x][y]);
            }
            System.out.println();
        }

        System.out.println();
        return costTable;
    }

    private List<Point> getAllFood(PacCell[][] state){
        return PacUtils.findFood( state);
    }

}