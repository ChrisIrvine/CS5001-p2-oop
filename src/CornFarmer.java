public class CornFarmer extends AbstractItem {

    private int[] deny = {-1, -2, 1, 2};
    private final int PRODUCEVALUE = 5;
    private final int PRODUCTION = 5;

    CornFarmer(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.grid.registerItem(x, y, this);
    }

    @Override
    protected int getStock() {
        return this.grid.getStockAt(xCoordinate, yCoordinate);
    }

    int getPRODUCEVALUE() {
        return this.PRODUCEVALUE;
    }

    int getPRODUCTION() {
        return this.PRODUCTION;
    }

    @Override
    protected void addToStock(int nutrition) {
        if(nutrition < 0) {
            this.addToStock(Math.abs(nutrition));
        } else if(nutrition > 0) {
            this.grid.stock[xCoordinate][yCoordinate] += nutrition;
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if(nutrition < 0) {
            this.reduceStock(Math.abs(nutrition));
        } else if((this.getStock() - nutrition) <= 0) {
            this.grid.emptyStockAt(xCoordinate, yCoordinate);
        } else {
            System.out.println(this.getStock() - nutrition);
            this.grid.stock[xCoordinate][yCoordinate] = this.getStock() - nutrition;
        }
    }

    @Override
    public void process(TimeStep timeStep) {
        int delay = 4;
        boolean foundFarmer = false;
        if(timeStep.getValue() % delay == 0) {
            for (int i = 0; i < this.deny.length; i++) {
                if(i % 2 == 0) { //deny[i] must refer to height
                    if(this.yCoordinate + deny[i] >= 0 && this.yCoordinate + deny[i] < this.grid.getWidth()) {
                        if(this.grid.getItem(xCoordinate, (yCoordinate + deny[i])) instanceof CornFarmer ||
                                this.grid.getItem(xCoordinate, (yCoordinate + deny[i])) instanceof RadishFarmer ) {
                            foundFarmer = true;
                        }
                    }
                } else if (i % 2 != 0) { //deny[i] must refer to width
                    if(this.xCoordinate + deny[i] >= 0 && this.xCoordinate + deny[i] < this.grid.getWidth()) {
                        if(this.grid.getItem((xCoordinate + deny[i]), yCoordinate) instanceof CornFarmer ||
                                this.grid.getItem((xCoordinate + deny[i]), yCoordinate) instanceof RadishFarmer ) {
                            foundFarmer = true;
                        }
                    }
                }
            }
            if (!foundFarmer) {
                int production = this.PRODUCEVALUE * this.PRODUCTION;
                this.grid.addToStockAt(this.xCoordinate, this.yCoordinate, production);
                grid.recordProduction(production);
            }
        }
    }

    @Override
    public String toString() {
        return ("Corn(" + this.getStock() + ")");
    }
}
