import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RadishFarmerTest {
    RadishFarmer radish;
    TimeStep tS;
    Grid grid;

    @Before
    public void setUp() {
        grid = new Grid(3, 5);
        radish = new RadishFarmer(grid, 1, 1);
        tS = new TimeStep();
    }

    @Test
    public void getProduceValue() {
        assertEquals(1, radish.getPRODUCEVALUE());
    }

    @Test
    public void getProduction() {
        assertEquals(10, radish.getPRODUCTION());
    }

    @Test
    public void getStock() {
        assertEquals(0, radish.getStock());
    }

    @Test
    public void addToStock() {
        radish.addToStock(4);
        assertEquals(4, radish.getStock());
        radish.addToStock(-5);
        assertEquals(4, radish.getStock());
    }

    @Test
    public void reduceStock() {
        radish.addToStock(4);
        radish.reduceStock(2);
        assertEquals(2, radish.getStock());
        radish.reduceStock(-1);
        assertEquals(1, radish.getStock());
        radish.reduceStock(10);
        assertEquals(0, radish.getStock());
    }

    @Test
    public void process() {
        tS.increment();
        tS.increment();
        radish.process(tS);
        assertEquals(10, radish.getStock());
    }
}