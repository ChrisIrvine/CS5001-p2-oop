import org.junit.Before;

import static org.junit.Assert.*;

public class GridTest {
    private Grid grid;
    private Grid grid2;


    @Before
    public void setUp() {
        //    RadishFarmer rad;
        //    HorizontalTransporter hz;
        //    Beaver beaver;
        //    //Rabbit bugs;
        //TimeStep ts = new TimeStep();
        grid = new Grid(3, 1);
        new CornFarmer(grid, 0, 0);
        new Beaver(grid, 2, 0);
        new VerticalTransporter(grid, 1, 0, 10);
        grid2 = new Grid(1, 3);
        new RadishFarmer(grid2, 0, 0);
        new Rabbit(grid2, 0, 2);
        new HorizontalTransporter(grid2, 0, 1, 10);
    }

    @org.junit.Test
    public void getWidth() {
        assertEquals(1, grid.getWidth());
    }

    @org.junit.Test
    public void getHeight() {
        assertEquals(3, grid.getHeight());
    }

    @org.junit.Test
    public void getItem() {
        CornFarmer tim = new CornFarmer(grid, 0, 0);
        assertEquals(tim, grid.getItem(0, 0));
        assertNull(grid.getItem(-1, -1));
    }

    @org.junit.Test
    public void getStockAt() {
        assertEquals(0, grid.getStockAt(-1, -1));
        assertEquals(0, grid.getStockAt(0, 0));
    }

    @org.junit.Test
    public void emptyStockAt() {
        grid.addToStockAt(0, 0, 10);
        assertEquals(10, grid.getStockAt(0, 0));
        grid.emptyStockAt(0, 0);
        assertEquals(0, grid.getStockAt(0, 0));
        grid.emptyStockAt(-1, -1);
    }

    @org.junit.Test
    public void addToStockAt() {
        grid.addToStockAt(0, 0, 10);
        assertEquals(10, grid.getStockAt(0, 0));
        grid.addToStockAt(0, 0, -10);
        assertEquals(20, grid.getStockAt(0, 0));
    }

    @org.junit.Test
    public void reduceStockAt() {
        grid.setStockAt(0, 0, 20);
        grid.reduceStockAt(0, 0, -10);
        assertEquals(10, grid.getStockAt(0, 0));
        grid.reduceStockAt(0, 0, 5);
        assertEquals(5, grid.getStockAt(0, 0));
    }

    @org.junit.Test
    public void setStockAt() {
        System.out.println(grid.getItem(2, 0));
        grid.setStockAt(-1, -1, 20);
        grid.setStockAt(0, 2, 48);
        assertEquals(48, grid.getStockAt(2, 0));
        grid.setStockAt(0, 0, 20);
        assertEquals(20, grid.getStockAt(0, 0));
        grid.setStockAt(0, 0, -10);
        assertEquals(10, grid.getStockAt(0, 0));
    }

    @org.junit.Test
    public void processItems() {
        Game game = new Game(grid);
        game.run(50);
        assertEquals(300, grid.getTotalProduction());
        assertEquals(235, grid.getTotalConsumption());

        game = new Game(grid2);
        game.run(50);
        assertEquals(160, grid2.getTotalProduction());
        assertEquals(128, grid2.getTotalConsumption());
    }

    @org.junit.Test
    public void recordProduction() {
        grid.recordProduction(-5);
        assertEquals(5, grid.getTotalProduction());
        grid.recordProduction(5);
        assertEquals(10, grid.getTotalProduction());
    }

    @org.junit.Test
    public void getTotalProduction() {
        assertEquals(0, grid.getTotalProduction());
    }

    @org.junit.Test
    public void recordConsumption() {
        grid.recordConsumption(5);
        assertEquals(5, grid.getTotalConsumption());
        grid.recordConsumption(-5);
        assertEquals(10, grid.getTotalConsumption());
    }

    @org.junit.Test
    public void getTotalConsumption() {
        assertEquals(0, grid.getTotalConsumption());
    }
}