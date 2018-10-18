/**
 * Implementation of the AbstractItem abstract class that will produce 10
 * radishes (with 1 nutritional value each) every 3rd time step. RadishFarmer
 * objects will only produce Radishes if there are no other Farmer objects in
 * the immediate vicinity (1 left, 1 right, 1 up, 1 down).
 */
public class RadishFarmer extends AbstractItem{

    /**Area to search for other farmers*/
    private int[] deny = {-1, -1, 1, 1};
    /**nutritional value of a radish*/
    private final int PRODUCEVALUE = 1;
    /**number of radishes produced*/
    private final int PRODUCTION = 10;

    /**
     * Constructor method for RadishFarmer. Uses attributes inherited from
     * AbstractItem to hold the grid, x and yCoordinates for this object.
     * Once attributes are assigned, the RadishFarmer is registered into the
     * grid.
     * @param grid - Matrix where the RadishFarmer 'lives'
     * @param x - height coordinate
     * @param y - width coordinate
     */
    RadishFarmer(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.grid.registerItem(x, y, this);
    }

    /**
     * Accessor method that will return the current stock of this object.
     * @return - the current stock of this object
     */
    @Override
    protected int getStock() {
        return this.grid.getStockAt(xCoordinate, yCoordinate);
    }

    /**
     * Accessor method to return the nutritional value for a radish.
     * @return - the nutritional value for a radish.
     */
    int getPRODUCEVALUE() {
        return this.PRODUCEVALUE;
    }

    /**
     * Accessor method to return the number of radishes produced.
     * @return - the number of radishes produced.
     */
    int getPRODUCTION() {
        return this.PRODUCTION;
    }

    /**
     * Mutator method that will add a given amount of nutrition to the stock
     * that this object holds (in the stock matrix). Includes negative number
     * handling.
     * @param nutrition - amount of nutrition to add
     */
    @Override
    protected void addToStock(int nutrition) {
        if(nutrition < 0) {
            this.addToStock(Math.abs(nutrition));
        } else if(nutrition > 0) {
            this.grid.stock[xCoordinate][yCoordinate] += nutrition;
        }
    }

    /**
     * Mutator method that will subtract a given amount of nutrition to the
     * stock that this object holds (in the stock matrix). Includes negative
     * number handling. Will not subtract below 0.
     * @param nutrition - amount of nutrition to subtract
     */
    @Override
    protected void reduceStock(int nutrition) {
        if(nutrition < 0) {
            this.reduceStock(Math.abs(nutrition));
        } else if((this.getStock() - nutrition) <= 0) {
            this.grid.emptyStockAt(xCoordinate, yCoordinate);
        } else {
            this.grid.stock[xCoordinate][yCoordinate]
                    = this.getStock() - nutrition;
        }
    }

    /**
     * Method that will produce stock according to the class PRODUCTION *
     * PRODUCEVALUE, if the current time step is a multiple of 3, and there are
     * no other farmer objects within the deny area (class attribute deny[]).
     * @param timeStep - current time-step
     */
    @Override
    public void process(TimeStep timeStep) {
        boolean foundFarmer = false;
        //Check that this time step is a multiple of 3
        if(timeStep.getValue() % 3 == 0) {
            for (int i = 0; i < this.deny.length; i++) {
                if(i % 2 == 0) { //deny[i] must refer to height
                    //Bounds checking
                    if(this.yCoordinate + deny[i] >= 0 && this.yCoordinate +
                            deny[i] < this.grid.getWidth()) {
                        //Check for farmer objects in deny area
                        if(this.grid.getItem(xCoordinate, (yCoordinate +
                                deny[i])) instanceof CornFarmer ||
                                this.grid.getItem(xCoordinate, (yCoordinate +
                                        deny[i])) instanceof RadishFarmer ) {
                            //we are good to produce radishes
                            foundFarmer = true;
                        }
                    }
                } else if (i % 2 != 0) { //deny[i] must refer to width
                    //Bounds checking
                    if(this.xCoordinate + deny[i] >= 0 && this.xCoordinate +
                            deny[i] < this.grid.getWidth()) {
                        //Check for farmer objects in the deny area
                        if(this.grid.getItem((xCoordinate + deny[i]),
                                yCoordinate) instanceof CornFarmer ||
                                this.grid.getItem((xCoordinate + deny[i]),
                                        yCoordinate) instanceof RadishFarmer ) {
                            //We are good to produce
                            foundFarmer = true;
                        }
                    }
                }
            }
            //Check that we are good to produce, if we are then produce!
            if (!foundFarmer) {
                int production = this.PRODUCEVALUE * this.PRODUCTION;
                this.grid.addToStockAt(this.xCoordinate, this.yCoordinate,
                        production);
                grid.recordProduction(production);
            }
        }
    }

    /**
     * toString override method that will print out the type of farmer followed
     * by the current stock the object holds.
     * @return - formatted string
     */
    @Override
    public String toString() {
        return ("Radish(" + this.getStock() + ")");
    }
}
