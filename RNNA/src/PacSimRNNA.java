import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import pacsim.*;

/**
 * University of Central Florida
 * CAP4630 - Spring 2010
 * Authors: Bailey Brooks and Ross Wagner
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

    }

    @Override
    public PacFace action( Object state ){
        PacFace face = null;
        return face;
    }
}