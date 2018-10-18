/**
 * Implementation of the abstract class, AbstractItem, that will consume up to
 * 5 units of nutrition immediately after being passed any number of nutrition.
 * Beavers will keep up to 50 stock after each timestep. Anything over the 50 is
 * wasted.
 */
public class Beaver extends AbstractItem {

    /**Grid object that the Beaver lives in*/
    private Grid grid;
    /**Height coordinate of the Beaver on the grid*/
    private int xCoordinate;
    /**Width coordinate of the Beaver on the grid*/
    private int yCoordinate;
    /**The rate of consumption for a Beaver*/
    private final int CONSUMPTION = 5;

    /**
     * Constructor for the Beaver Object. Takes a Grid and two integers as
     * parameters, which dictates the Beaver Object's location on the grid.
     * Once object has been created with parameters stored, the object is
     * registered onto the grid, overwriting the null value.
     * @param grid - matrix where the Beaver 'lives'
     * @param x - Height Coordinate
     * @param y - Width Coordinate
     */
    Beaver(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.grid.registerItem(x, y, this);
    }

    /**
     * Method to check if the Rabbit has stock to consume. Will always attempt
     * to consume 8.
     * @param timeStep - The current time-step
     */
    @Override
    public void process(TimeStep timeStep) {
        if(this.getStock() > 0) {
            this.reduceStock(this.CONSUMPTION);
        }
    }

    /**
     * Accessor method which will fetch the stock for this Beaver object from
     * the stock matrix.
     * @return - the current stock for this Rabbit object.
     */
    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    /**
     * Mutator method to add stock to the current stock value of this Beaver
     * Object. Stock will not be increased over 55, wasting any excess stock
     * passed to the Rabbit. This is because the beaver will consume 5 stock
     * immediately before storing the 50 excess stock. Also includes negative
     * number handling.
     * @param nutrition - amount of nutrition to add
     */
    @Override
    protected void addToStock(int nutrition) {
        if (nutrition < 0 ) {
            addToStock(Math.abs(nutrition));
        } else {
            if ((this.getStock() + nutrition) > 50) {
                this.grid.stock[xCoordinate][yCoordinate] = 55;
            } else if (nutrition > 0) {
                this.grid.stock[xCoordinate][yCoordinate] += nutrition;
            }
        }
    }

    /**
     * Mutator method to subtract stock from the current stock value of this
     * Beaver Object. Stock cannot be decreased below 0 and will consume either
     * 5 or, in the case of less than 5 stock present, all the current stock.
     * Also includes negative number handling.
     * @param nutrition - amount of nutrition to subtract
     */
    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            reduceStock(Math.abs(nutrition));
        } else {
            if ((this.getStock() - nutrition) < 0) {
                this.reduceStock(this.getStock());
            } else if (this.getStock() < this.CONSUMPTION) {
                this.grid.stock[xCoordinate][yCoordinate] -= nutrition;
                this.grid.recordConsumption(nutrition);
            } else if (this.getStock() >= this.CONSUMPTION) {
                this.grid.stock[xCoordinate][yCoordinate] -= this.CONSUMPTION;
                this.grid.recordConsumption(this.CONSUMPTION);
            }
        }
    }

    /**
     * toString override to confrom with the expectations of the assignment.
     * Will print out the name of the object and then the current stock that the
     * object holds.
     * @return - formatted string as described above.
     */
    @Override
    public String toString() {
        return ("Beaver(" + this.getStock() + ")");
    }
}
