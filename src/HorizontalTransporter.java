/**
 * Implementation of the AbstractItem abstract class that will check for
 * CornFarmer/RadishFarmer objects on either side of the HorizontalTransporter
 * object (looking along the row). If there is a farmer with stock this object
 * will then look for any consumer objects (Rabbit/Beaver) on the same row. If
 * this object is positioned between the farmer and consumer, then an amount of
 * stock, up to the limit of this object's capacity attribute will be moved from
 * the farmer to the consumer.
 */
public class HorizontalTransporter extends AbstractItem{

    /**The limit that this object can move in a single time step*/
    private int capacity;
    /**
     * Array to hold the location of all farmer objects in the row of this
     * object.
     */
    private int[] farmResults;
    /**
     * Array to hold the location of all consumer objects in the row of this
     * object.
     */
    private int[] consumerResults;

    /**
     * Constructor Method for HorizontalTransporter class. Will take a grid, x
     * and y coordinate to pass into the AbstractItem class variables. Then this
     * will set the capacity for the transporter (held in this class). Next this
     * object is registered to the location at the given grid at the given
     * location. Finally the farmResults and consumerResults arrays are
     * initialised.
     * @param grid - matrix where this HorizontalTransporter will 'live'
     * @param x - height coordinate
     * @param y - width coordinate
     * @param cap - maximum load this transporter can move in one time step
     */
    HorizontalTransporter (Grid grid, int x, int y, int cap) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.capacity = cap;
        this.grid.registerItem(x, y, this);
        farmResults = new int[this.grid.getWidth()];
        consumerResults = new int[this.grid.getWidth()];
        for(int i = 0; i < this.grid.getWidth(); i++) {
            farmResults[i] = -1;
        }
        for(int i = 0; i < this.grid.getWidth(); i++) {
            consumerResults[i] = -1;
        }
    }

    /**
     * Method to check for farms with stock and then for consumers. If the
     * transporter is positioned between any of these, and there is still stock
     * then attempt to move stock up to the capacity from the farmer to the
     * consumer.
     * @param timeStep - current time-step
     */
    @Override
    public void process(TimeStep timeStep) {
        this.farmCheck();
        for(int i: farmResults) {
            if(i != -1) {
                destinationCheck(i);
                for (int j : consumerResults) {
                    if(j != -1) {
                        if(positionCheck(i, j)) {
                            transportGoods(i, j, this.stockCheck(i));
                        }
                    }

                }
            }
        }
    }

    /**
     * Transport goods from a start location to an end location on the same
     * row as this transporter.
     * @param start - start location on the row
     * @param finish - end location on the row
     * @param nutrition - amount of stock to transport.
     */
    private void transportGoods(int start, int finish, int nutrition) {
        this.grid.reduceStockAt(xCoordinate, start, nutrition);
        this.grid.addToStockAt(xCoordinate, finish, nutrition);
    }

    /**
     * Method to check that this transporter object is positioned between the
     * start point and end point of the move.
     * @param farmPos - starting position of the move (expected to be a farmer)
     * @param conPos - ending position of the move (expected to be a consumer)
     * @return - true if this object is between farmPos and conPos
     */
    private boolean positionCheck(int farmPos, int conPos) {
        if(farmPos < this.yCoordinate && this.yCoordinate < conPos) {
            return true;
        } else return conPos < this.yCoordinate && this.yCoordinate < farmPos;
    }

    /**
     * Method to check the stock at a given location on the same row of this
     * object.
     * @param y - position on this row to check
     * @return - either 0 (in case of no stock), the amount of stock the object
     * has (if below capacity or above 0), or capacity.
     */
    private int stockCheck(int y) {
        int tempStock = this.grid.getStockAt(xCoordinate, y);

        if (tempStock == 0) {
            return 0;
        } else if((tempStock - this.capacity) < 0 && tempStock > 0) {
            return tempStock;
        } else {
            return this.capacity;
        }
    }

    /**
     * Method to find farms on this row, if they have stock add them to the
     * farmResults array.
     */
    private void farmCheck() {
        for(int i = 0; i < this.grid.getWidth(); i++) {
            if(this.grid.getItem(xCoordinate, i) instanceof CornFarmer ||
                    this.grid.getItem(xCoordinate, i) instanceof RadishFarmer) {
                if(this.grid.getItem(xCoordinate, i).getStock() > 0) {
                    farmResults[i] = i;
                }
            }
        }
    }

    /**
     * Method to find consumers that are on the other side of this object from
     * the farmer object that is being tested.
     * @param farmPos - location of farmer being tested.
     */
    private void destinationCheck(int farmPos) {
        if (farmPos > this.yCoordinate) {
            for (int i = 0; i < this.yCoordinate; i++) {
                if(this.grid.getItem(xCoordinate, i) instanceof Rabbit ||
                        this.grid.getItem(xCoordinate, i) instanceof Beaver) {
                    consumerResults[i] = i;
                }
            }
        } else {
            for(int i = this.grid.getWidth(); i > this.yCoordinate; i--) {
                if(this.grid.getItem(xCoordinate, i) instanceof Rabbit ||
                        this.grid.getItem(xCoordinate, i) instanceof Beaver) {
                    consumerResults[i] = i;
                }
            }
        }
    }

    /**
     * Accessor method that will get the current stock of this object from the
     * stock matrix.
     * @return - the current stock of this object from the stock matrix.
     */
    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    /**
     * Accessor method that will add stock to this objects stock matrix
     * location, contains negative number handling.
     * @param nutrition - amount of nutrition to add
     */
    @Override
    protected void addToStock(int nutrition) {
        if (nutrition < 0) {
            this.addToStock(Math.abs(nutrition));
        } else {
            if (nutrition < this.capacity) {
                this.grid.stock[xCoordinate][yCoordinate] += nutrition;
            } else {
                this.grid.stock[xCoordinate][yCoordinate] += this.capacity;
            }
        }
    }

    /**
     * Accessor method that will subtract stock to this objects stock matrix
     * location, contains negative number handling.
     * @param nutrition - amount of nutrition to subtract
     */
    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            this.reduceStock(Math.abs(nutrition));
        } else {
            if ((this.grid.getStockAt(this.xCoordinate, this.yCoordinate)
                    - nutrition) < 0) {
                this.grid.emptyStockAt(xCoordinate, yCoordinate);
            } else if (nutrition < this.capacity) {
                this.grid.stock[xCoordinate][yCoordinate] -= nutrition;
            } else {
                this.grid.stock[xCoordinate][yCoordinate] -= this.capacity;
            }
        }
    }

    /**
     * Overridden toString method that will print out the type of transporter
     * this class is.
     * @return - formatted string
     */
    @Override
    public String toString() { return ("HT"); }
}
