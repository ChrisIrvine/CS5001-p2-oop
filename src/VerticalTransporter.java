public class VerticalTransporter extends AbstractItem {
    private Grid grid;
    private int xCoordinate;
    private int yCoordinate;
    private int capacity;
    private int stock = 0;

    VerticalTransporter (Grid grid, int x, int y, int cap) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.capacity = cap;
        grid.registerItem(this.xCoordinate, this.yCoordinate, this);
    }

    @Override
    public void process(TimeStep timeStep) {
        if(grid.getStockAt(xCoordinate, yCoordinate - 1) >= this.capacity) {
            grid.reduceStockAt(xCoordinate, yCoordinate - 1, this.capacity);
            grid.addToStockAt(xCoordinate, yCoordinate  + 1, this.capacity);
        } else if(grid.getStockAt(xCoordinate, yCoordinate  + 1) >= this.capacity) {
            grid.reduceStockAt(xCoordinate, yCoordinate  + 1, this.capacity);
            grid.addToStockAt(xCoordinate, yCoordinate - 1, this.capacity);
        } else if(grid.getStockAt(xCoordinate, yCoordinate - 1) > 0 &&
                grid.getStockAt(xCoordinate, yCoordinate - 1) < capacity) {
            int tempStock = grid.getStockAt(xCoordinate, yCoordinate - 1);
            grid.reduceStockAt(xCoordinate, yCoordinate - 1, tempStock);
            grid.addToStockAt(xCoordinate, yCoordinate  + 1, tempStock);
        } else if(grid.getStockAt(xCoordinate, yCoordinate  + 1) > 0 &&
                grid.getStockAt(xCoordinate, yCoordinate  + 1) < capacity) {
            int tempStock = grid.getStockAt(xCoordinate, yCoordinate  + 1);
            grid.reduceStockAt(xCoordinate, yCoordinate - 1, tempStock);
            grid.addToStockAt(xCoordinate, yCoordinate + 1, tempStock);
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

        if ((nutrition+this.stock) > this.capacity) {
            this.setStock(10);
        } else if (nutrition < this.capacity) {
            this.stock += nutrition;
        } else {
            this.stock += this.capacity;
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if ((this.stock - nutrition) < 0) {
            this.setStock(0);
        } else if (nutrition < this.capacity) {
            this.stock -= nutrition;
        } else {
            this.stock -= this.capacity;
        }
    }

    @Override
    public String toString() {
        return ("      VT      ");
    }
}
