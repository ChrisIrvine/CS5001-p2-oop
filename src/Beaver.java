public class Beaver extends AbstractItem {

    private Grid grid;
    private int xCoordinate;
    private int yCoordinate;
    private int stock;
    private final int CONSUMPTION = 5;

    Beaver(Grid grid, int x, int y) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        grid.registerItem(x, y, this);
    }

    @Override
    public void process(TimeStep timeStep) {
        if(this.stock > 0) {
            this.reduceStock(this.CONSUMPTION);
            grid.recordConsumption(this.CONSUMPTION);
        }
    }

    protected void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    protected int getStock() {
        return this.stock;
    }

    @Override
    protected void addToStock(int nutrition) {
        if (nutrition > 0) {
            this.stock += nutrition;
        } else if ((this.stock + nutrition) > 50) {
            this.setStock(50);
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition > 0) {
            this.stock -= nutrition;
        } else if  ((this.stock - nutrition) < 0) {
            this.setStock(0);
        }
    }
}
