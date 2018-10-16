public class RadishFarmer extends AbstractItem{
    private int[] deny = {1, 1, 1, 1};
    private int stock = 0;
    private final int PRODUCEVALUE = 1;
    private final int PRODUCTION = 10;

    RadishFarmer(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        grid.registerItem(x, y, this);
    }

    @Override
    protected int getStock() {
        return this.stock;
    }

    void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    protected void addToStock(int nutrition) {
        if(nutrition > 0) { this.stock += nutrition; }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if (this.stock - nutrition < 0) {
            this.setStock(0);
        } else {
            this.stock -= nutrition;
        }
    }

    int getProduceValue() {
        return this.PRODUCEVALUE;
    }

    public int getProduction() { return this.PRODUCTION; }

    @Override
    public void process(TimeStep timeStep) {
        int delay = 3;
        boolean goodToProduce = false;
        if(timeStep.getValue() % delay == 0) {
            for (int i = 0; i < this.deny.length; i++) {
                if ((this.xCoordinate + this.deny[i]) < 0
                        || (this.xCoordinate + this.deny[i])
                        > grid.getHeight()
                        || (this.yCoordinate + this.deny[i])
                        < 0
                        || (this.yCoordinate + this.deny[i])
                        > grid.getWidth()) {
                } else if (i % 2 == 0 && grid.getItem(this.xCoordinate +
                        this.deny[i], this.yCoordinate) instanceof CornFarmer) {
                    goodToProduce = false;
                    return;
                } else if (i % 2 != 0 && grid.getItem(this.xCoordinate,
                        this.yCoordinate + this.deny[i]) instanceof
                        CornFarmer) {
                    goodToProduce = false;
                    return;
                } else {
                    goodToProduce = true;
                }
            }
            if (goodToProduce) {
                int production = this.PRODUCEVALUE * this.PRODUCTION;
                addToStock(production);
                grid.recordProduction(production);
            }
        }
    }
}
