package main.java;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.NullPointerException;


public class PotentialSolution implements Comparable<PotentialSolution> {

    /**
     * Point weight is a class that bundles together the point location and the distance it is from the previous point.
     * It is meant to be used
     *
    * */
    class PointCost{
        Point point;
        int cost;

        public PointCost(Point point, int cost){
            this.point = point;
            this.cost = cost;
        }
    }

    int cost;
    private List<PointCost> path;

    // constructor
    public PotentialSolution(){
        this.cost = 0;
        this.path = new ArrayList();
    }

    public void addToPath(Point p, int cost){
        path.add(new PointCost(p,cost));
        this.cost += cost;
    }

    /**
     * Returns a copy of this PotentialSolution
     * */
    public PotentialSolution clone()
    {
        PotentialSolution ps = new PotentialSolution();
        ps.setCost(this.cost);
        ps.setPath(this.path);
        return ps;

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

    public List<PointCost> getPath(){
        return this.path;
    }
    


    public void setPath(List<PointCost> path){
        this.path = path;
    }

    public void setCost(int cost){
        this.cost = cost;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("cost=%d : ",this.cost));
        int len = path.size();
        for (int i =0 ; i< len;i++){
            Point p =path.get(i).point;
            sb.append(String.format("[(%d,%d),%d]",p.x,p.y,path.get(i).cost));

        }
        return sb.toString();
    }
}