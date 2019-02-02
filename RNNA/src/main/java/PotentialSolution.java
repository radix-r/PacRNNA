package main.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.NullPointerException;


public class PotentialSolution implements Comparable<PotentialSolution>, Cloneable {

    /**
     * Point weight is a class that bundles together the point location and the distance it is from the previous point.
     *
     *
    * */
    class PointCost{
        Point point;
        int cost;
        int index; // associated index in food array

        public PointCost(Point point, int cost, int index){
            this.point = point;
            this.cost = cost;
            this.index = index;
        }
    }

    int cost;
    boolean[] visited; // keep track of stops / foods that this solution has visited
    private List<PointCost> stops; // location and distance of stops for food
    private int numFood;


    // constructor
    public PotentialSolution(int numFood){

        this.cost = 0;
        this.stops = new ArrayList();
        this.numFood = numFood;
        this.visited = new boolean[numFood];

    }

    public void addToStops(Point p, int cost, int index){

        this.visited[index]=true;
        stops.add(new PointCost(p,cost,index));
        this.cost += cost;

    }

    /**
     * Returns a copy of this PotentialSolution
     * */
    @Override
    public Object clone ()throws CloneNotSupportedException
    {
        return super.clone();
        /*
        PotentialSolution ps = new PotentialSolution(this.numFood);
        ps.setCost(this.cost);
        ps.setStops(this.stops);
        ps.visited = this.visited;
        //ps.numFood = this.numFood;
        System.out.printf("Cloning : %s\n",ps.toString());
        return ps;
        */

    }

    @Override
    public int compareTo(PotentialSolution other){
        if (other == null){
            throw new NullPointerException();
        }

        if (this.cost < other.cost){
            return -1;
        }else if(this.cost == other.cost){
            return 0;
        }else {
            return 1;
        }
    }

    public int getCost(){
        return this.cost;
    }

    public List<PointCost> getStops(){
        return this.stops;
    }

    public PointCost getCurrentStop(){
        // returns most recently added stop in for of point weight object
        return this.stops.get(stops.size()-1);
    }
    


    public void setStops(List<PointCost> path){
        this.stops = path;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("cost=%d : ",this.cost));
        int len = stops.size();
        for (int i =0 ; i< len;i++){
            Point p =stops.get(i).point;
            sb.append(String.format("[(%d,%d),%d]",p.x,p.y,stops.get(i).cost));

        }
        return sb.toString();
    }
}