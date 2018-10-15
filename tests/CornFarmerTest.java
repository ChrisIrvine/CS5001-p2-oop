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
        grid.registerItem(1, 1, corn);
    }

    @Test
    public void getProduceValue() {
        assertEquals(5, corn.getProduceValue());
    }

    @Test
    public void setProduceValue() {
        corn.setProduceValue(7);
        assertEquals(7, corn.getProduceValue());
    }

    @Test
    public void getStock() {
        assertEquals(0, corn.getStock());
    }

    @Test
    public void addToStock() {
        corn.addToStock(4);
        assertEquals(4, corn.getStock());

    }

    @Test
    public void reduceStock() {
        corn.addToStock(4);
        corn.reduceStock(2);
        assertEquals(2, corn.getStock());
    }

    @Test
    public void setStock() {
        corn.setStock(0);
        assertEquals(0, corn.getStock());
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