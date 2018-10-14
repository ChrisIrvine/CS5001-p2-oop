import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmerTest {
    Farmer corn;
    Farmer radish;
    TimeStep tS;
    BasicGrid grid;

    @Before
    public void setUp() {
        grid = new BasicGrid(3, 5);
        corn = new Farmer(Farmer.FarmerType.CornFarmer);
        radish = new Farmer(Farmer.FarmerType.RadishFarmer);
        tS = new TimeStep();
        grid.registerItem(1, 1, corn);
        grid.registerItem(3, 5, radish);
    }

    @Test
    public void isCorn() {
        assertEquals(Farmer.FarmerType.CornFarmer, corn.getType());
        assertEquals(5, corn.getType().getProduction());
        assertEquals(4, corn.getType().getDelay());
    }

    @Test
    public void isRadish() {
        assertEquals(Farmer.FarmerType.RadishFarmer, radish.getType());
        assertEquals(10, radish.getType().getProduction());
        assertEquals(3, radish.getType().getDelay());
    }

    @Test
    public void process() {
        tS.increment();
        tS.increment();
        radish.process(tS);
        assertEquals(10, radish.getStock());
        tS.increment();
        corn.process(tS);
        assertEquals(25 ,corn.getStock());
    }

    @Test
    public void getStock() {
        assertEquals(0, corn.getStock());
        assertEquals(0, radish.getStock());
    }

    @Test
    public void addToStock() {
        corn.addToStock(4);
        assertEquals(4, corn.getStock());
        radish.addToStock(4);
        assertEquals(4, radish.getStock());

    }

    @Test
    public void reduceStock() {
        corn.addToStock(4);
        radish.addToStock(4);
        corn.reduceStock(2);
        radish.reduceStock(2);
        assertEquals(2, corn.getStock());
        assertEquals(2, radish.getStock());
        corn.reduceStock(10);
        radish.reduceStock(10);
        assertEquals(0, corn.getStock());
        assertEquals(0, radish.getStock());
    }
}