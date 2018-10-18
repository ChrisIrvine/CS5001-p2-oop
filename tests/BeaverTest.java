import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeaverTest {
    private Beaver beaver;
    private Grid grid;
    private TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(3, 5);
        beaver = new Beaver(grid, 0, 4);
        ts = new TimeStep();
    }

    @Test
    public void process() {
        beaver.addToStock(5);
        beaver.process(ts);
        assertEquals(0, beaver.getStock());
        assertEquals(5, grid.getTotalConsumption());
    }

    @Test
    public void getStock() {
        assertEquals(0, beaver.getStock());
    }

    @Test
    public void addToStock() {
        beaver.addToStock(3);
        assertEquals(3, beaver.getStock());
        beaver.addToStock(-4);
        assertEquals(7, beaver.getStock());
        beaver.addToStock(453);
        assertEquals(55, beaver.getStock());
    }

    @Test
    public void reduceStock() {
        beaver.addToStock(9);
        assertEquals(9, beaver.getStock());
        beaver.reduceStock(5);
        assertEquals(4, beaver.getStock());
        beaver.reduceStock(-5);
        assertEquals(0, beaver.getStock());
        assertEquals(9, grid.getTotalConsumption());
    }
}