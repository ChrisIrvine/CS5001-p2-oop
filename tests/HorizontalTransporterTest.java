import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HorizontalTransporterTest {
    private HorizontalTransporter hz;
    private Grid grid;
    private Grid grid2;
    private TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(1, 3);
        hz = new HorizontalTransporter(grid, 0, 1, 10);
        ts = new TimeStep();
        new CornFarmer(grid, 0, 0);
        new Rabbit(grid, 0, 2);

        grid2 = new Grid(1, 3);
        new CornFarmer(grid2, 0, 2);
        new HorizontalTransporter(grid2, 0, 1, 10);
        new Rabbit(grid2, 0, 0);
    }

    @Test
    public void process() {
        Game game = new Game(grid);
        game.run(4);
        assertEquals(8, grid.getTotalConsumption());
        assertEquals(15, grid.getStockAt(0, 0));

        Game game2 = new Game(grid2);
        game2.run(4);
        assertEquals(8, grid2.getTotalConsumption());
        assertEquals(15, grid2.getStockAt(0, 2));
    }

    @Test
    public void getStock() {
        assertEquals(0, hz.getStock());
    }


    @Test
    public void addToStock() {
        hz.addToStock(10);
        assertEquals(10, hz.getStock());
        hz.addToStock(5);
        assertEquals(15, hz.getStock());
        hz.addToStock(-4);
        assertEquals(19, hz.getStock());
        hz.addToStock(5);
        assertEquals(24, hz.getStock());
    }

    @Test
    public void reduceStock() {
        hz.addToStock(10);
        hz.reduceStock(5);
        assertEquals(5, hz.getStock());
        hz.reduceStock(-4);
        assertEquals(1, hz.getStock());
        hz.reduceStock(5);
        assertEquals(0, hz.getStock());
    }
}