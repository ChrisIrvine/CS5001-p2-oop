import static org.junit.Assert.*;

public class BasicGridTest {
    BasicGrid grid = new BasicGrid(3, 5);
    Farmer tim = new Farmer(Farmer.FarmerType.CornFarmer);

    @org.junit.Test
    public void getWidth() {
        assertEquals(3, grid.getWidth());
    }

    @org.junit.Test
    public void getHeight() {
        assertEquals(5, grid.getHeight());
    }

    @org.junit.Test
    public void registerItem() {
        grid.registerItem(1,1, tim);
        assertEquals(tim, grid.getItem(1, 1));
    }

    @org.junit.Test
    public void getItem() {
        assertTrue(grid.getItem(1, 1) instanceof EmptyItem);
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