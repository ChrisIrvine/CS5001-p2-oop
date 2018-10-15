public class RadishFarmer extends AbstractItem{
    private int[] deny = {1, 1, 1, 1};
    private int stock = 0;
    private int produceValue = 1;
    private int production = 10;

    RadishFarmer(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
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
        this.stock += nutrition;
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (this.stock - nutrition < 0) {
            this.stock = 0;
        } else {
            this.stock -= nutrition;
        }
    }

    int getProduceValue() {
        return this.produceValue;
    }

    void setProduceValue(int value) {
        this.produceValue = value;
    }

    public int getProduction() {
        return this.production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

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
                addToStock(this.produceValue * production);
            }
        }
    }
}
