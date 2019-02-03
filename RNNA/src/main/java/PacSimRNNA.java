package main.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

import main.java.PotentialSolution;
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
    // he entries in this array should be ordered according to increasing values of the x-value, and for each value of x, according to increasing values of the y-value

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

            // start timer
            long startTime = System.nanoTime();



            // calc cost table
            int[][] costTable = makeCostTable(grid, pc);

            // food array
            List<Point> food = getAllFood(grid);


            // calc the stuff bro


            // init our population with each food pel
            int lenFood = food.size();
            int step =0;
            for (int i = 0;i<lenFood;i++){
                PotentialSolution ps = new PotentialSolution(lenFood);
                // i+1 because first food at index 0 but it is 1 in cost table
                ps.addToStops(food.get(i), costTable[0][i+1],i);
                potentialSolutions.add(ps);

            }

            //print the first step
            Collections.sort(potentialSolutions);
            System.out.printf("Population at step %d :\n", step);

            printPopulation(potentialSolutions);



            try{
                boolean done = false;
                while (!done) {
                    step++;
                    done = true;

                    // iterate through potentialSolutions and append each uneaten dot to and its cost to path
                    int lenPS = potentialSolutions.size();
                    for (int i = 0; i < lenPS; i++) {


                        // add copies with tied nearest neighbor. Look at cost table for ties.
                        // get index in food array of most recent stop
                        int currentStop = potentialSolutions.get(i).getCurrentStop().index;
                        boolean[] visited = potentialSolutions.get(i).visited;

                        // get next unvisited. list for ties. using cost table
                        List<Integer> closest = getNextClosestUnvisited(visited, currentStop, costTable);

                        //debug
                        //System.out.println(closest);

                        int cost = closest.remove(0);


                        PotentialSolution clone;
                        if (closest.size() >= 1) {
                            //ties found. make copie

                            clone = potentialSolutions.get(i).clone();

                            done = false;

                        } else {
                            // all food eaten
                            continue;
                        }

                        // get index of first closest unvisited food
                        int foodIndex = closest.remove(0);

                        potentialSolutions.get(i).addToStops(food.get(foodIndex), cost, foodIndex);


                        // make copies for ties
                        for (Integer fi : closest) {
                            Point p = food.get(fi);
                            PotentialSolution temp = clone.clone();
                            temp.addToStops(p, cost, fi);
                            //System.out.printf("Cloned : %s\n",temp.toString());
                            potentialSolutions.add(temp);
                        }

                        // stop when all paths have eaten all dots

                        // update the size
                        lenPS = potentialSolutions.size();
                    }
                }
            }catch (CloneNotSupportedException c){
                System.out.println("Failed clone");
                System.exit(1);
            }


            System.out.printf("Population at step %d :\n", step);
            // sort potential solutions
            Collections.sort(potentialSolutions);

            // print final population
            printPopulation(potentialSolutions);

            // generate path from best solution
            path = getFinalPath(grid, potentialSolutions.get(0));

            // stop timer
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);

            System.out.printf("Time to generate plan: %d msec\n",duration/1000000);
            System.out.println("\nSolution moves:");

        }


        Point next = path.remove( 0 );
        PacFace face = PacUtils.direction( pc.getLoc(), next );
        System.out.printf( "%5d : From [ %2d, %2d ] go %s%n",
                ++simTime, pc.getLoc().x, pc.getLoc().y, face );
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
        System.out.println("Cost table:\n");
        for (int x = 0; x < size; x++){
            System.out.printf("\t");
            for (int y = 0; y < size; y++){
                System.out.printf("%4d", costTable[x][y]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        return costTable;
    }

    private void printPopulation(List<PotentialSolution> potentialSolutions){

        int lenPS = potentialSolutions.size();
        for (int i = 0; i < lenPS; i++){
            String str = potentialSolutions.get(i).toString();
            String out = String.format("\t%d : %s",i,str);
            System.out.println(out);
        }
        System.out.printf("\n");

    }

    private List<Point> getAllFood(PacCell[][] state){
        System.out.println("Food Array\n");

        List<Point> food = PacUtils.findFood( state);

        int lenFood = food.size();
        for (int i = 0; i< lenFood; i++){
            Point p = food.get(i);
            System.out.printf("%d : (%d,%d)\n",i,p.x,p.y);
        }

        System.out.println();
        return food;
    }

    /**
     * returns the min cost followed by the indexes of points with that cost in terms of food array indexing
     *
     * */
    private List<Integer> getNextClosestUnvisited(boolean[]visited, int currentStop, int[][]costTable){

        int len = visited.length;
        List<Integer> minIndexes = new ArrayList<>();

        // find min
        int min = Integer.MAX_VALUE;
        for (int i =1;i<=len;i++){
            if (visited[i-1]){
                // skip if we have already been there
                continue;
            }
            if (costTable[currentStop+1][i] < min){
                min = costTable[currentStop+1][i];
            }

        }
        minIndexes.add(min);
        for (int i = 1; i <= len; i++){
            if (visited[i-1]){
                // skip if we have already been there
                continue;
            }
            if (costTable[currentStop+1][i] == min){
                minIndexes.add(i-1);
            }

        }
        return minIndexes;


    }

    private List<Point> getFinalPath(PacCell[][] G, PotentialSolution bestPath){

        // find pacman first for starting location
        Point start = PacUtils.findPacman( G ).getLoc();

        List<Point> finalList = new ArrayList<>();
        List<Point> tempList = null;

        //finalList.add(start);

        // path from start to first point
        tempList = BFSPath.getPath(G, start, bestPath.stops.get(0).point);
        for (int j = 0; j < tempList.size(); j++) {
            finalList.add(tempList.get(j));
        }

        for(int i = 0; i < bestPath.stops.size() - 1; i++){
            //System.out.println(bestPath.stops.get(i).point);
            tempList = BFSPath.getPath(G, bestPath.stops.get(i).point, bestPath.stops.get(i + 1).point);
            for (int j = 0; j < tempList.size(); j++) {
                finalList.add(tempList.get(j));
            }
        }
        //System.out.println();
        //for(int i = 0; i < finalList.size(); i++) {
            //System.out.println(finalList.get(i));
        //}

        return finalList;

    }

}
