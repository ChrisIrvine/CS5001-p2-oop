public class VerticalTransporter extends AbstractItem {
    private Grid grid;
    private int xCoordinate;
    private int yCoordinate;
    private final int CAPACITY;
    private int stock = 0;

    VerticalTransporter (Grid grid, int x, int y, int cap) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.CAPACITY = cap;
        grid.registerItem(this.xCoordinate, this.yCoordinate, this);
    }

    @Override
    public void process(TimeStep timeStep) {

    }

    @Override
    protected int getStock() {
        return 0;
    }

    @Override
    protected void addToStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if (nutrition < this.CAPACITY) {
            this.stock += nutrition;
        } else {
            this.stock += this.CAPACITY;
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if (nutrition < this.CAPACITY) {
            this.stock -= nutrition;
        } else {
            this.stock -= this.CAPACITY;
        }
    }
}
