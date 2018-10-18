public class VerticalTransporter extends AbstractItem {
    private Grid grid;
    private int xCoordinate;
    private int yCoordinate;
    private int capacity;

    VerticalTransporter (Grid grid, int x, int y, int cap) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.capacity = cap;
        this.grid.registerItem(x, y, this);
    }

    @Override
    public void process(TimeStep timeStep) {
        int foundFarm = this.farmCheck();
        if(foundFarm >= 0) {
            int foundDest = this.destinationCheck();
            if(foundDest >= 0) {
                if(positionCheck(foundFarm, foundDest)) {
                    this.transportGoods(foundFarm, foundDest,
                            this.stockCheck(foundFarm));
                }
            }
        }
    }

    private void transportGoods(int start, int finish, int nutrition) {
        this.grid.reduceStockAt(start, yCoordinate, nutrition);
        this.grid.addToStockAt(finish, yCoordinate, nutrition);

    }

    private boolean positionCheck(int farmPos, int conPos) {
        if(farmPos < this.xCoordinate && this.xCoordinate < conPos) {
            return true;
        } else return conPos < this.xCoordinate && this.xCoordinate < farmPos;
    }

    private int stockCheck(int y) {
        int tempStock = this.grid.getStockAt(this.xCoordinate, y);

        if((tempStock - this.capacity) < 0 && tempStock > 0) {
            return tempStock;
        } else {
            return this.capacity;
        }
    }

    private int farmCheck() {
        for(int i = 0; i < this.grid.getHeight(); i++) {
            if(this.grid.getItem(i, yCoordinate) instanceof CornFarmer ||
                    this.grid.getItem(i, yCoordinate) instanceof RadishFarmer) {
                if(this.grid.getItem(i, yCoordinate).getStock() > 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int destinationCheck() {
        for(int i = 0; i < this.grid.getHeight(); i++) {
            if(this.grid.getItem(i, yCoordinate) instanceof Rabbit ||
                    this.grid.getItem(i, yCoordinate) instanceof Beaver) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int getStock() {
        return this.grid.getStockAt(this.xCoordinate, this.yCoordinate);
    }

    @Override
    protected void addToStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if (nutrition < this.capacity) {
            this.grid.stock[xCoordinate][yCoordinate] += nutrition;
        } else {
            this.grid.stock[xCoordinate][yCoordinate] += this.capacity;
        }
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0) {
            nutrition = Math.abs(nutrition);
        }

        if ((this.grid.getStockAt(this.xCoordinate, this.yCoordinate) - nutrition) < 0) {
            this.grid.emptyStockAt(xCoordinate, yCoordinate);
        } else if (nutrition < this.capacity) {
            this.grid.stock[xCoordinate][yCoordinate] -= nutrition;
        } else {
            this.grid.stock[xCoordinate][yCoordinate] -= this.capacity;
        }
    }

    @Override
    public String toString() {
        return ("VT");
    }
}
