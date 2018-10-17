import org.junit.Before;

import static org.junit.Assert.*;

public class GridTest {
    Grid grid;
    CornFarmer tim;
    HorizontalTransporter hz;
    Beaver beaver;
    TimeStep ts;

    @Before
    public void setUp() {
        grid = new Grid(1, 3);
        tim = new CornFarmer(this.grid, 0, 0);
        hz = new HorizontalTransporter(this.grid, 0, 1, 10);
        beaver = new Beaver(this.grid, 0, 2);
        ts = new TimeStep();
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
        assertEquals(tim, grid.getItem(0, 0));
        assertEquals(hz, grid.getItem(0, 1));
        assertEquals(beaver, grid.getItem(0, 2));
        for(int i = 0; i < 4; i++) {
            grid.processItems(ts);
            ts.increment();
        }
        assertEquals(25, grid.getTotalProduction());
        assertEquals(15, grid.getStockAt(0, 0));
        assertEquals(10, grid.getStockAt(0, 2));
        assertEquals(5, grid.getTotalConsumption());
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