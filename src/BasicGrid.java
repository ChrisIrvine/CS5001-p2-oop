/**
 *
 */
public class BasicGrid extends AbstractGrid {

    public BasicGrid(int height, int width) {

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

    }

    @Override
    public int getTotalProduction() {

    }

    @Override
    public void recordConsumption(int nutrition) {

    }

    @Override
    public int getTotalConsumption() {
        return 0;
    }
}
