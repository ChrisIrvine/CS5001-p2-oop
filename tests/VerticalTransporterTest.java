import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VerticalTransporterTest {
    VerticalTransporter vt;
    Grid grid;
    TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(1, 3);
        vt = new VerticalTransporter(grid, 0, 1, 10);
        ts = new TimeStep();
    }

    @Test
    public void process() {
        grid.addToStockAt(0, 0, 10);
        vt.process(ts);
        assertEquals(0, grid.getStockAt(0, 0));
        assertEquals(10, grid.getStockAt(0, 2));
        grid.addToStockAt(0, 2, 10);
        vt.process(ts);
        assertEquals(10, grid.getStockAt(0, 0));
        assertEquals(10, grid.getStockAt(0, 2));
    }

    @Test
    public void getStock() {
        assertEquals(0, vt.getStock());
    }

    @Test
    public void setStock() {
        vt.setStock(11);
        assertEquals(10, vt.getStock());
        vt.setStock(5);
        assertEquals(5, vt.getStock());
        vt.setStock(-7);
        assertEquals(7, vt.getStock());
    }

    @Test
    public void addToStock() {
        vt.addToStock(10);
        assertEquals(10, vt.getStock());
        vt.setStock(0);
        vt.addToStock(5);
        assertEquals(5, vt.getStock());
        vt.addToStock(-4);
        assertEquals(9, vt.getStock());
        vt.addToStock(5);
        assertEquals(10, vt.getStock());
    }

    @Test
    public void reduceStock() {
        vt.setStock(10);
        vt.reduceStock(5);
        assertEquals(5, vt.getStock());
        vt.reduceStock(-4);
        assertEquals(1, vt.getStock());
        vt.reduceStock(5);
        assertEquals(0, vt.getStock());
    }
}