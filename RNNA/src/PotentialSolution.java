
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.NullPointerException;
import pacsim.*;


public class PotentialSolution implements Comparable<PotentialSolution> {

    int cost;
    private List<Point> path;

    // constructor
    public PotentialSolution(){
        this.cost = 0;
        this.path = new ArrayList();
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



}