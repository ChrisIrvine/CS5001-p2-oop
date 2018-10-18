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
        bugs.addToStock(8);
        bugs.process(ts);
        assertEquals(0, bugs.getStock());
        assertEquals(8, grid.getTotalConsumption());
    }


    @Test
    public void getStock() {
        assertEquals(0, bugs.getStock());
    }

    @Test
    public void addToStock() {
        bugs.addToStock(5);
        assertEquals(5, bugs.getStock());
        bugs.addToStock(16);
        assertEquals(8, bugs.getStock());
        bugs.addToStock(-8);
        assertEquals(8, bugs.getStock());
    }

    @Test
    public void reduceStock() {
        bugs.addToStock(16);
        assertEquals(8, bugs.getStock());
        bugs.reduceStock(8);
        assertEquals(0, bugs.getStock());
        assertEquals(8, grid.getTotalConsumption());
        bugs.addToStock(5);
        assertEquals(5, bugs.getStock());
        bugs.reduceStock(8);
        assertEquals(0, bugs.getStock());
        assertEquals(13, grid.getTotalConsumption());
    }
}