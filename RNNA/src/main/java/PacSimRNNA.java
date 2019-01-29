package main.java;

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
    private List<main.java.PotentialSolution> potentialSolutions;
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
        potentialSolutions = new ArrayList<main.java.PotentialSolution>();

    }



    @Override
    public PacFace action( Object state ){
        PacCell[][] grid = (PacCell[][]) state;
        PacmanCell pc = PacUtils.findPacman( grid );

        // calculate only if list is empty (once)
        if( path.isEmpty() ) {

            // food array
            food = getAllFood(grid);

            // calc cost table


            // calc the stuff bro
            main.java.PotentialSolution ps = new main.java.PotentialSolution();


            // sort our potential solutions
            Collections.sort(potentialSolutions);
        }



        PacFace face = null;
        return face;
    }

    private List<Point> getAllFood(PacCell[][] state){
        return PacUtils.findFood( state);
    }
}