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
        bugs.process(ts);
        assertEquals(0, bugs.getStock());
        assertEquals(8, grid.getTotalConsumption());
    }


    @Test
    public void getStock() {
    }

    @Test
    public void addToStock() {
    }

    @Test
    public void reduceStock() {
    }
}