/**
 *
 */
public class Grid extends AbstractGrid {

    private int totalProduction;
    private int totalConsumption;

    public Grid(int width, int height) {
        grid = new AbstractItem[width][height];
        totalProduction = 0;
        for(int w = 0; w < width; w++) {
            for(int h = 0; h < height; h++) {
                grid[w][h] = new EmptyItem();
            }
        }
    }
    /**
     *
     * @return
     */
    @Override
    public int getWidth() {
        return grid.length;
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeight() {
        return grid[0].length;
    }

    /**
     *
     * @param xCoordinate
     * @param yCoordinate
     * @param item
     */
    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
        grid[xCoordinate][yCoordinate] = item;
    }

    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        return grid[xCoordinate][yCoordinate];
    }

    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        return stock[xCoordinate][yCoordinate];
    }

    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {
        stock[xCoordinate][yCoordinate] = 0;
    }

    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        stock[xCoordinate][yCoordinate] += nutrition;
    }

    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        stock[xCoordinate][yCoordinate] -= nutrition;
    }

    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        stock[xCoordinate][yCoordinate] = nutrition;
    }

    @Override
    public void processItems(TimeStep timeStep) {

    }

    @Override
    public void recordProduction(int nutrition) {
        System.out.println("adding " + nutrition + " nutrition");
        if(nutrition > 0) {
            System.out.println(nutrition);
            this.totalProduction += nutrition;
        }
    }

    @Override
    public int getTotalProduction() {
        return this.totalProduction;
    }

    @Override
    public void recordConsumption(int nutrition) {
        if (nutrition > 0) {
            this.totalProduction += nutrition;
        }
    }

    @Override
    public int getTotalConsumption() {
        return this.totalConsumption;
    }
}
