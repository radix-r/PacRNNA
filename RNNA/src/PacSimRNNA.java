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


        // calculate only if list is empty (once)
        if( path.isEmpty() ) {

            // calc cost table

            // food array

            // calc the stuff bro
            PotentialSolution ps = new PotentialSolution();

        }

        Collections.sort(potentialSolutions);

        PacFace face = null;
        return face;
    }
}