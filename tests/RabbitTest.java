import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RabbitTest {
    Rabbit bugs;
    Grid grid;
    TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(3, 5);
        bugs = new Rabbit(grid, 0, 4);
        ts = new TimeStep();
    }

    @Test
    public void process() {
        bugs.setStock(8);
        bugs.process(ts);
        assertEquals(0, bugs.getStock());
        assertEquals(8, grid.getTotalConsumption());
    }

    @Test
    public void setStock() {
        bugs.setStock(8);
        assertEquals(8, bugs.getStock());
        bugs.setStock(9);
        assertEquals(8, bugs.getStock());
        bugs.setStock(-2);
        assertEquals(2, bugs.getStock());
        bugs.setStock(-9);
        assertEquals(8, bugs.getStock());
    }

    @Test
    public void getStock() {
        bugs.setStock(5);
        assertEquals(5, bugs.getStock());
    }

    @Test
    public void addToStock() {
        bugs.addToStock(3);
        assertEquals(3, bugs.getStock());
        bugs.addToStock(6);
        assertEquals(8, bugs.getStock());
        bugs.setStock(0);
        bugs.addToStock(-4);
        assertEquals(0, bugs.getStock());
    }

    @Test
    public void reduceStock() {
        bugs.setStock(8);
        bugs.reduceStock(4);
        assertEquals(4, bugs.getStock());
        bugs.reduceStock(5);
        assertEquals(0, bugs.getStock());
        bugs.addToStock(8);
        bugs.reduceStock(-7);
        assertEquals(1, bugs.getStock());
    }
}