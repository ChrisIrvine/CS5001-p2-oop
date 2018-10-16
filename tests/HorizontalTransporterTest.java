import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HorizontalTransporterTest {
    HorizontalTransporter hz;
    Grid grid;
    TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(3, 1);
        hz = new HorizontalTransporter(grid, 1, 0, 10);
        ts = new TimeStep();
    }

    @Test
    public void process() {
        grid.addToStockAt(0, 0, 10);
        hz.process(ts);
        assertEquals(0, grid.getStockAt(0, 0));
        assertEquals(10, grid.getStockAt(2, 0));
        grid.addToStockAt(2, 0, 10);
        hz.process(ts);
        assertEquals(10, grid.getStockAt(0, 0));
        assertEquals(10, grid.getStockAt(2, 0));
    }

    @Test
    public void getStock() {
        assertEquals(0, hz.getStock());
    }

    @Test
    public void setStock() {
        hz.setStock(11);
        assertEquals(10, hz.getStock());
        hz.setStock(5);
        assertEquals(5, hz.getStock());
        hz.setStock(-7);
        assertEquals(7, hz.getStock());
    }

    @Test
    public void addToStock() {
        hz.addToStock(10);
        assertEquals(10, hz.getStock());
        hz.setStock(0);
        hz.addToStock(5);
        assertEquals(5, hz.getStock());
        hz.addToStock(-4);
        assertEquals(9, hz.getStock());
        hz.addToStock(5);
        assertEquals(10, hz.getStock());
    }

    @Test
    public void reduceStock() {
        hz.setStock(10);
        hz.reduceStock(5);
        assertEquals(5, hz.getStock());
        hz.reduceStock(-4);
        assertEquals(1, hz.getStock());
        hz.reduceStock(5);
        assertEquals(0, hz.getStock());
    }
}