import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VerticalTransporterTest {
    private VerticalTransporter vt;
    private Grid grid;
    private Grid grid2;
    private TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(3, 1);
        vt = new VerticalTransporter(grid, 1, 0, 10);
        ts = new TimeStep();
        new CornFarmer(grid, 0, 0);
        new Rabbit(grid, 2, 0);

        grid2 = new Grid(3, 1);
        new VerticalTransporter(grid2, 1, 0, 10);
        new CornFarmer(grid2, 2, 0);
        new Rabbit(grid2, 0, 0);
    }

    @Test
    public void process() {
        Game g1 = new Game(grid);
        g1.run(4);
        assertEquals(8, grid.getTotalConsumption());
        assertEquals(15, grid.getStockAt(0, 0));

        Game g2 = new Game(grid2);
        g2.run(4);
        assertEquals(8, grid2.getTotalConsumption());
        assertEquals(15, grid2.getStockAt(2, 0));
    }

    @Test
    public void getStock() {
        assertEquals(0, vt.getStock());
    }

    @Test
    public void addToStock() {
        vt.addToStock(10);
        assertEquals(10, vt.getStock());
        vt.addToStock(5);
        assertEquals(15, vt.getStock());
        vt.addToStock(-4);
        assertEquals(19, vt.getStock());
        vt.addToStock(5);
        assertEquals(24, vt.getStock());
    }

    @Test
    public void reduceStock() {
        vt.addToStock(10);
        vt.reduceStock(5);
        assertEquals(5, vt.getStock());
        vt.reduceStock(-4);
        assertEquals(1, vt.getStock());
        vt.reduceStock(5);
        assertEquals(0, vt.getStock());
    }
}