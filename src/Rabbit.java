public class Rabbit extends AbstractItem{

    protected Grid grid;
    private int xCoordinate;
    private int yCoordinate;
    private int stock;
    private final int CONSUMPTION = 8;

    Rabbit(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.grid.registerItem(x, y, this);
    }

    @Override
    public void process(TimeStep timeStep) {
        if(this.stock == 8 || this.stock > this.CONSUMPTION) {
            this.reduceStock(this.CONSUMPTION);
            grid.recordConsumption(this.CONSUMPTION);
            this.setStock(0);
        } else if (this.stock > 0) {
            this.reduceStock(this.stock);
            grid.recordConsumption(this.stock);
        }
    }

    void setStock(int stock) {
        if (stock > 8) {
            this.setStock(8);
        } else if (stock < 0 ){
            this.setStock(Math.abs(stock));
        } else {
            this.stock = stock;
        }
    }

    @Override
    protected int getStock() {
        return this.stock;
    }

    @Override
    protected void addToStock(int nutrition) {
        if (nutrition < 0 ) {
            nutrition = Math.abs(nutrition);
        }

        if ((this.stock + nutrition) > 8) {
            this.setStock(8);
        } else if (nutrition > 0) {
            this.stock += nutrition;
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if ((this.stock - nutrition) < 0) {
            this.setStock(0);
        } else if (nutrition > 0) {
            this.stock -= nutrition;
        }
    }

    @Override
    public String toString() {
        return ("Rabbit(" + this.getStock() + ")");
    }
}