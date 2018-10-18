import org.junit.Before;

import static org.junit.Assert.*;

public class GridTest {
    Grid grid;
//    CornFarmer tim;
//    RadishFarmer rad;
//    HorizontalTransporter hz;
//    Beaver beaver;
//    //Rabbit bugs;
    TimeStep ts;

    @Before
    public void setUp() {
        ts = new TimeStep();
        grid = new Grid(3, 1);
        new CornFarmer(grid, 0, 0);
        new Beaver(grid, 2, 0);
        new VerticalTransporter(grid, 1, 0, 10);

    }

    @org.junit.Test
    public void getWidth() {
        assertEquals(3, grid.getWidth());
    }

    @org.junit.Test
    public void getHeight() {
        assertEquals(1, grid.getHeight());
    }

    @org.junit.Test
    public void getItem() {
    }

    @org.junit.Test
    public void getStockAt() {
    }

    @org.junit.Test
    public void emptyStockAt() {
    }

    @org.junit.Test
    public void addToStockAt() {
    }

    @org.junit.Test
    public void reduceStockAt() {
    }

    @org.junit.Test
    public void setStockAt() {
    }

    @org.junit.Test
    public void processItems() {
        Game game = new Game(grid);
        game.run(50);
        assertEquals(300, grid.getTotalProduction());
        assertEquals(235, grid.getTotalConsumption());
    }

    @org.junit.Test
    public void recordProduction() {
    }

    @org.junit.Test
    public void getTotalProduction() {
    }

    @org.junit.Test
    public void recordConsumption() {
    }

    @org.junit.Test
    public void getTotalConsumption() {
    }
}