public class Rabbit extends AbstractItem{

    protected Grid grid;
    private int xCoordinate;
    private int yCoordinate;
    private final int CONSUMPTION = 8;

    Rabbit(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.grid.registerItem(x, y, this);
    }

    @Override
    public void process(TimeStep timeStep) {
        if(this.getStock() > 0) {
            this.reduceStock(this.CONSUMPTION);
        }
    }

    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    @Override
    protected void addToStock(int nutrition) {
        if (nutrition < 0 ) {
            nutrition = Math.abs(nutrition);
        }

        if ((this.getStock() + nutrition) > 8) {
            this.grid.stock[xCoordinate][yCoordinate] = 8;
        } else if (nutrition > 0) {
            this.grid.stock[xCoordinate][yCoordinate] += nutrition;
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if ((this.getStock() - nutrition) < 0) {
            this.reduceStock(this.getStock());
        } else if (this.getStock() < this.CONSUMPTION) {
            this.grid.stock[xCoordinate][yCoordinate] -= nutrition;
            this.grid.recordConsumption(nutrition);
        } else if (this.getStock() >= this.CONSUMPTION ) {
            this.grid.stock[xCoordinate][yCoordinate] -= this.CONSUMPTION;
            this.grid.recordConsumption(this.CONSUMPTION);
        }
    }

    @Override
    public String toString() {
        return ("Rabbit(" + this.getStock() + ")");
    }
}
