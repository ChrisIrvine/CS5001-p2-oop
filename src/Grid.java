/**
 *
 */
public class Grid extends AbstractGrid {

    private int totalProduction;
    private int totalConsumption;

    public Grid(int width, int height) {
        grid = new AbstractItem[width][height];
        stock = new int[width][height];
    }
    /**
     *
     * @return
     */
    @Override
    public int getWidth() {
        return grid[0].length;
    }

    /**
     *
     * @return
     */
    @Override
    public int getHeight() {
        return grid.length;
    }

    /**
     *
     * @param xCoordinate
     * @param yCoordinate
     * @param item
     */
    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem item) {
        this.grid[xCoordinate][yCoordinate] = item;
    }

    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        if(xCoordinate >= 0 && xCoordinate < this.getHeight() &&
                yCoordinate >= 0 && yCoordinate < this.getWidth()) {
            return this.grid[xCoordinate][yCoordinate];
        }
        return null;
    }

    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        if(xCoordinate >= 0 && xCoordinate < this.getHeight() &&
                yCoordinate >= 0 && yCoordinate < this.getWidth()) {
            return this.stock[xCoordinate][yCoordinate];
        }
        return 0;
    }

    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {
        this.stock[xCoordinate][yCoordinate] = 0;

    }

    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        this.getItem(xCoordinate, yCoordinate).addToStock(nutrition);
    }

    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        this.getItem(xCoordinate, yCoordinate).reduceStock(nutrition);
    }

    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        if(xCoordinate > 0 && xCoordinate < this.getWidth() &&
                yCoordinate > 0 && yCoordinate < this.getHeight()) {
            int tempStock = this.getStockAt(xCoordinate, yCoordinate);
            this.reduceStockAt(xCoordinate, yCoordinate, tempStock);
            this.addToStockAt(xCoordinate, yCoordinate, nutrition);
        }
    }

    @Override
    public void processItems(TimeStep timeStep) {
        for(int i = 1; i < 4; i++) {
            for(int y = 0; y < this.getWidth(); y++) {
                for(int x = 0; x < this.getHeight(); x++) {
                    switch (i) {
                        case 1:
                            if(this.getItem(x, y) instanceof CornFarmer ||
                                    this.getItem(x, y) instanceof RadishFarmer) {
                                this.getItem(x, y).process(timeStep);
                            }
                            break;
                        case 2:
                            if(this.getItem(x, y) instanceof HorizontalTransporter ||
                                    this.getItem(x, y) instanceof VerticalTransporter) {
                                this.getItem(x, y).process(timeStep);
                            }
                            break;
                        case 3:
                            if(this.getItem(x, y) instanceof Rabbit ||
                                    this.getItem(x, y) instanceof Beaver) {
                                this.getItem(x, y).process(timeStep);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @Override
    public void recordProduction(int nutrition) {
        if(nutrition < 0) {
            this.recordConsumption(Math.abs(nutrition));
        }

        this.totalProduction += nutrition;
    }

    @Override
    public int getTotalProduction() {
        return this.totalProduction;
    }

    @Override
    public void recordConsumption(int nutrition) {
        if(nutrition < 0) {
            this.recordConsumption(Math.abs(nutrition));
        }

        this.totalConsumption += nutrition;
    }

    @Override
    public int getTotalConsumption() {
        return this.totalConsumption;
    }
}
