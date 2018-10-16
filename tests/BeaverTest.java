import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BeaverTest {
    Beaver beaver;
    Grid grid;
    TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(3, 5);
        beaver = new Beaver(grid, 0, 4);
        ts = new TimeStep();
    }

    @Test
    public void process() {
        beaver.setStock(5);
        beaver.process(ts);
        assertEquals(0, beaver.getStock());
        assertEquals(5, grid.getTotalConsumption());
    }

    @Test
    public void setStock() {
        beaver.setStock(8);
        assertEquals(8, beaver.getStock());
        beaver.setStock(51);
        assertEquals(50, beaver.getStock());
        beaver.setStock(-2);
        assertEquals(2, beaver.getStock());
        beaver.setStock(-51);
        assertEquals(50, beaver.getStock());
    }

    @Test
    public void getStock() {
        beaver.setStock(5);
        assertEquals(5, beaver.getStock());
    }

    @Test
    public void addToStock() {
        beaver.addToStock(3);
        assertEquals(3, beaver.getStock());
        beaver.addToStock(453);
        assertEquals(50, beaver.getStock());
        beaver.setStock(0);
        beaver.addToStock(-4);
        assertEquals(4, beaver.getStock());
    }

    @Test
    public void reduceStock() {
        beaver.setStock(8);
        beaver.reduceStock(4);
        assertEquals(4, beaver.getStock());
        beaver.reduceStock(5);
        assertEquals(0, beaver.getStock());
        beaver.addToStock(8);
        beaver.reduceStock(-7);
        assertEquals(1, beaver.getStock());
    }
}