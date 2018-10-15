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
        grid.registerItem(1, 1, radish);
    }

    @Test
    public void getProduceValue() {
        assertEquals(1, radish.getProduceValue());
    }

    @Test
    public void getProduction() {
        assertEquals(10, radish.getProduction());
    }

    @Test
    public void setProduction() {
        radish.setProduction(14);
        assertEquals(14, radish.getProduction());
    }

    @Test
    public void setProduceValue() {
        radish.setProduceValue(7);
        assertEquals(7, radish.getProduceValue());
    }

    @Test
    public void getStock() {
        assertEquals(0, radish.getStock());
    }

    @Test
    public void addToStock() {
        radish.addToStock(4);
        assertEquals(4, radish.getStock());

    }

    @Test
    public void reduceStock() {
        radish.addToStock(4);
        radish.reduceStock(2);
        assertEquals(2, radish.getStock());
    }

    @Test
    public void setStock() {
        radish.setStock(0);
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