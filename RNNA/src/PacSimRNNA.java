import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import pacsim.*;

/**
 *
 * Implements Repetitive Nearest Neighbor Search Algorithm for Pac-Man
 *
 * @author Bailey Brooks & Ross Wagner
 *
 *
 *
 *
 **/


public class PacSimRNNA implements PacAction {

    private List<Point> path;
    private int simTime;

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

        // calc cost table

        // food array
    }



    @Override
    public PacFace action( Object state ){


        // calculate only if list is empty (once)
        if( path.isEmpty() ) {

            // calc the stuff bro

        }


        PacFace face = null;
        return face;
    }
}