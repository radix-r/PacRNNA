import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class PotentialSolutionTest {

    static main.java.PotentialSolution psTester;

    @BeforeClass
    public static void testSetup(){
        psTester = new main.java.PotentialSolution();
    }

    @Test
    public void addToPathTest() {
        Point p =new Point();
        p.x = 1;
        p.y=2;

        psTester.addToPath(p, 3);

        Assert.assertEquals("addToPath","cost=3 : [(1,2),3]", psTester.toString());
    }

    @Test
    public void cloneTest() {
    }

    @Test
    public void compareToTest() {
    }

    @Test
    public void setPathTest() {
    }

    @Test
    public void setCostTest() {
    }

    @Test
    public void toStringTest() {
    }
}