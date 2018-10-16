import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CornFarmerTest {
    CornFarmer corn;
    TimeStep tS;
    Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(3, 5);
        corn = new CornFarmer(grid, 1, 1);
        tS = new TimeStep();
    }

    @Test
    public void getStock() {
        assertEquals(0, corn.getStock());
    }

    @Test
    public void getPRODUCEVALUE() { assertEquals(5, corn.getPRODUCEVALUE()); }

    @Test
    public void getPRODUCTION() { assertEquals(5, corn.getPRODUCTION()); }

    @Test
    public void addToStock() {
        corn.addToStock(4);
        assertEquals(4, corn.getStock());
        corn.addToStock(-5);
        assertEquals(4, corn.getStock());
    }

    @Test
    public void reduceStock() {
        corn.addToStock(4);
        corn.reduceStock(2);
        assertEquals(2, corn.getStock());
        corn.reduceStock(-1);
        assertEquals(1, corn.getStock());
        corn.reduceStock(10);
        assertEquals(0, corn.getStock());
    }

    @Test
    public void setStock() {
        corn.setStock(6);
        assertEquals(6, corn.getStock());
    }

    @Test
    public void process() {
        tS.increment();
        tS.increment();
        tS.increment();
        corn.process(tS);
        assertEquals(25, corn.getStock());
    }
}