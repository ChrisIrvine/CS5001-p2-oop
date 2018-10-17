public class HorizontalTransporter extends AbstractItem{
    private Grid grid;
    private int xCoordinate;
    private int yCoordinate;
    private int capacity;
    private int stock = 0;

    HorizontalTransporter (Grid grid, int x, int y, int cap) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.capacity = cap;
        this.grid.registerItem(this.xCoordinate, this.yCoordinate, this);
    }

    @Override
    public void process(TimeStep timeStep) {
        if(grid.getStockAt(xCoordinate - 1, yCoordinate) >= this.capacity) {
            grid.reduceStockAt(xCoordinate - 1, yCoordinate, this.capacity);
            grid.addToStockAt(xCoordinate + 1, yCoordinate, this.capacity);
        } else if(grid.getStockAt(xCoordinate + 1, yCoordinate) >= this.capacity) {
            grid.reduceStockAt(xCoordinate + 1, yCoordinate, this.capacity);
            grid.addToStockAt(xCoordinate -1, yCoordinate, this.capacity);
        } else if(grid.getStockAt(xCoordinate - 1, yCoordinate) > 0 &&
                grid.getStockAt(xCoordinate - 1, yCoordinate) < capacity) {
            int tempStock = grid.getStockAt(xCoordinate - 1, yCoordinate);
            grid.reduceStockAt(xCoordinate - 1, yCoordinate, tempStock);
            grid.addToStockAt(xCoordinate + 1, yCoordinate, tempStock);
        } else if(grid.getStockAt(xCoordinate + 1, yCoordinate) > 0 &&
                grid.getStockAt(xCoordinate + 1, yCoordinate) < capacity) {
            int tempStock = grid.getStockAt(xCoordinate + 1, yCoordinate);
            grid.reduceStockAt(xCoordinate - 1, yCoordinate, tempStock);
            grid.addToStockAt(xCoordinate + 1, yCoordinate, tempStock);
        }
    }

    @Override
    protected int getStock() {
        return this.stock;
    }

    protected void setStock(int stock) {
        if (stock > this.capacity) {
            this.setStock(this.capacity);
        } else if (stock < 0 ){
            this.setStock(Math.abs(stock));
        } else {
            this.stock = stock;
        }
    }

    @Override
    protected void addToStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if (nutrition < this.capacity) {
            this.grid.addToStockAt(xCoordinate, yCoordinate, nutrition);
        } else {
            this.grid.addToStockAt(xCoordinate, yCoordinate, this.capacity);
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if ((this.stock - nutrition) < 0) {
            this.grid.emptyStockAt(xCoordinate, yCoordinate);
        } else if (nutrition < this.capacity) {
            this.grid.reduceStockAt(xCoordinate, yCoordinate, nutrition);
        } else {
            System.out.println("reducing stock");
            this.grid.reduceStockAt(xCoordinate, yCoordinate, this.capacity);
        }
    }

    @Override
    public String toString() {
        return ("HT");
    }
}
