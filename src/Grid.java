/**
 * Implementation of the Abstract Class, AbstractGrid, to provide the
 * functionality required to play the game.
 */
public class Grid extends AbstractGrid {

    /**private int class variable to hold the total production of the game*/
    private int totalProduction;
    /**private int class variable to hold the total consumption of the game*/
    private int totalConsumption;

    /**
     * Constructor for grid class. Defines the width and height of the grid
     * and stock matrices.
     * @param width - width of the grid.
     * @param height - height of the grid.
     */
    public Grid(int width, int height) {
        grid = new AbstractItem[width][height];
        stock = new int[width][height];
    }

    /**
     * Accessor method to the width of the grid.
     * @return - integer equal to the width of the grid.
     */
    @Override
    public int getWidth() {
        return grid[0].length;
    }

    /**
     * Accessor method to the height of the grid.
     * @return - integer equal to the height of the grid.
     */
    @Override
    public int getHeight() {
        return grid.length;
    }

    /**
     * Mutator method to replace a null AbstractItem (default initialisation of
     * the grid elements) with an implemented AbstractItem (CornFarmer,
     * RadishFarmer ... Rabbit).
     * @param xCoordinate - (height) coordinate of the item
     * @param yCoordinate - (width) coordinate of the item
     * @param item - implemented AbstractItem object
     */
    @Override
    public void registerItem(int xCoordinate, int yCoordinate, AbstractItem
            item) {
        this.grid[xCoordinate][yCoordinate] = item;
    }

    /**
     * Accessor method that will check the coordinates are within the boundaries
     * of the grid matrix, before attempting to retrieve the an object at a
     * given set of coordinates. Returns null if invalid coordinates.
     * @param xCoordinate - height coordinate
     * @param yCoordinate - width coordinate
     * @return - implemented AbstractItem object
     */
    @Override
    public AbstractItem getItem(int xCoordinate, int yCoordinate) {
        if(xCoordinate >= 0 && xCoordinate < this.getHeight() &&
                yCoordinate >= 0 && yCoordinate < this.getWidth()) {
            return this.grid[xCoordinate][yCoordinate];
        }
        return null;
    }

    /**
     * Accessor method that will check the coordinates are within the boundaries
     * of the stock matrix, before attempting to retrieve the integer value
     * held at a given set of coordinates. Returns 0 if invalid coordinates.
     * @param xCoordinate - height coordinate
     * @param yCoordinate - width coordinate
     * @return - int stock value at given coordinates of stock matrix
     */
    @Override
    public int getStockAt(int xCoordinate, int yCoordinate) {
        if(xCoordinate >= 0 && xCoordinate < this.getHeight() &&
                yCoordinate >= 0 && yCoordinate < this.getWidth()) {
            return this.stock[xCoordinate][yCoordinate];
        }
        return 0;
    }

    /**
     * Mutator method that will set the stock value at a given set of
     * coordinates to 0.
     * @param xCoordinate - height coordinate
     * @param yCoordinate - width coordinate
     */
    @Override
    public void emptyStockAt(int xCoordinate, int yCoordinate) {
        this.stock[xCoordinate][yCoordinate] = 0;

    }

    /**
     * Mutator method that will add stock to a given set of coordinates. To
     * ensure that the rules of each item is adhered to, the addition is
     * done through the implemented AbstractItems's addToStock() method.
     * @param xCoordinate - height coordinate
     * @param yCoordinate - width coordinate
     * @param nutrition -  the amount of stock to add
     */
    @Override
    public void addToStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        this.getItem(xCoordinate, yCoordinate).addToStock(nutrition);
    }

    /**
     * Mutator method that will remove stock to a given set of coordinates. To
     * ensure that the rules of each item is adhered to, the addition is
     * done through the implemented AbstractItems's reduceStock() method.
     * @param xCoordinate - height coordinate
     * @param yCoordinate - width coordinate
     * @param nutrition -  the amount of stock to remove
     */
    @Override
    public void reduceStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        this.getItem(xCoordinate, yCoordinate).reduceStock(nutrition);
    }

    /**
     * Mutator method that will set the stock at a given set of coordinates, to
     * a given amount. To ensure that the rules of each item is adhered to, the
     * setting process is implemented using AbstractItems's reduceStock() and
     * addToStock() methods.
     * @param xCoordinate - height coordinate
     * @param yCoordinate - width coordinate
     * @param nutrition -  the amount to set the stock value to
     */
    @Override
    public void setStockAt(int xCoordinate, int yCoordinate, int nutrition) {
        if(xCoordinate > 0 && xCoordinate < this.getWidth() &&
                yCoordinate > 0 && yCoordinate < this.getHeight()) {
            int tempStock = this.getStockAt(xCoordinate, yCoordinate);
            this.reduceStockAt(xCoordinate, yCoordinate, tempStock);
            this.addToStockAt(xCoordinate, yCoordinate, nutrition);
        }
    }

    /**
     * Method to iterate three times through the grid matrix; searching for
     * Farmers > Transporters > Consumers. Each coordinate of the grid will be
     * evaluated for the appropraite instance (controlled by the switch
     * statement and the first for loop (i). If the correct instance of
     * AbstractItem is found the item is processed according to the value of
     * timeStep.
     * @param timeStep - The time step we are at. This value may be used to
     *                 determine production frequency of farmers.
     */
    @Override
    public void processItems(TimeStep timeStep) {
        for(int i = 1; i < 4; i++) {
            for(int x = 0; x < this.getHeight(); x++) {
                for(int y = 0; y < this.getWidth(); y++) {
                    switch (i) {
                        case 1:
                            if(this.getItem(x, y) instanceof CornFarmer ||
                                    this.getItem(x, y) instanceof RadishFarmer) {
                                this.getItem(x, y).process(timeStep);
                            }
                            break;
                        case 2:
                            if(this.getItem(x, y) instanceof
                                    HorizontalTransporter ||
                                    this.getItem(x, y) instanceof
                                            VerticalTransporter) {
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

    /**
     * Mutator method to increase the totalProduction value for this object.
     * Method contains a negative number check that will transform the number
     * into a positive number. This version of the game is not interested in
     * reducing the totalProduction.
     * @param nutrition -  the amount of production to record
     */
    @Override
    public void recordProduction(int nutrition) {
        if(nutrition < 0) {
            this.recordProduction(Math.abs(nutrition));
        }

        this.totalProduction += nutrition;
    }

    /**
     * Accessor method to retrieve the current int value of totalProduction
     * @return - current int value of totalProduction
     */
    @Override
    public int getTotalProduction() {
        return this.totalProduction;
    }

    /**
     * Mutator method to increase the totalConsumption value for this object.
     * Method contains a negative number check that will transform the number
     * into a positive number. This version of the game is not interested in
     * reducing the totalConsumption. 
     * @param nutrition -  the amount of consumption to record
     */
    @Override
    public void recordConsumption(int nutrition) {
        if(nutrition < 0) {
            this.recordConsumption(Math.abs(nutrition));
        }

        this.totalConsumption += nutrition;
    }

    /**
     * Accessor method to retrieve the current int value of totalConsumption
     * @return - current int value of totalConsumption
     */
    @Override
    public int getTotalConsumption() {
        return this.totalConsumption;
    }
}
