public class HorizontalTransporter extends AbstractItem{

    private int capacity;
    private int[] farmResults;
    private int[] consumerResults;

    HorizontalTransporter (Grid grid, int x, int y, int cap) {
        this.grid = grid;
        this.xCoordinate = x;
        this.yCoordinate = y;
        this.capacity = cap;
        this.grid.registerItem(x, y, this);
        farmResults = new int[this.grid.getWidth()];
        consumerResults = new int[this.grid.getWidth()];
        for(int i = 0; i < this.grid.getWidth(); i++) {
            farmResults[i] = -1;
        }
        for(int i = 0; i < this.grid.getHeight(); i++) {
            consumerResults[i] = -1;
        }

    }

    @Override
    public void process(TimeStep timeStep) {
        this.farmCheck();
        for(int i: farmResults) {
            if(i != -1) {
                destinationCheck(i);
                for (int j : consumerResults) {
                    if(j != -1) {
                        if(positionCheck(i, j)) {
                            transportGoods(i, j, this.stockCheck(i));
                        }
                    }

                }
            }
        }

//        if() {
//            int foundDest = this.destinationCheck();
//            if(foundDest >= 0) {
//                if(positionCheck(foundFarm, foundDest)) {
//                    this.transportGoods(foundFarm, foundDest,
//                            this.stockCheck(foundFarm));
//                }
//            }
//        }
    }

    private void transportGoods(int start, int finish, int nutrition) {
        this.grid.reduceStockAt(xCoordinate, start, nutrition);
        this.grid.addToStockAt(xCoordinate, finish, nutrition);
    }

    private boolean positionCheck(int farmPos, int conPos) {
        if(farmPos < this.yCoordinate && this.yCoordinate < conPos) {
            return true;
        } else return conPos < this.yCoordinate && this.yCoordinate < farmPos;
    }

    private int stockCheck(int y) {
        int tempStock = this.grid.getStockAt(xCoordinate, y);

        if (tempStock == 0) {
            return 0;
        } else if((tempStock - this.capacity) < 0 && tempStock > 0) {
            return tempStock;
        } else {
            return this.capacity;
        }
    }

    private void farmCheck() {
        for(int i = 0; i < this.grid.getWidth(); i++) {
            if(this.grid.getItem(xCoordinate, i) instanceof CornFarmer ||
                    this.grid.getItem(xCoordinate, i) instanceof RadishFarmer) {
                if(this.grid.getItem(xCoordinate, i).getStock() > 0) {
                    farmResults[i] = i;
                }
            }
        }
    }

    private void destinationCheck(int farmPos) {
        if (farmPos > this.yCoordinate) {
            for (int i = 0; i < this.yCoordinate; i++) {
                if(this.grid.getItem(xCoordinate, i) instanceof Rabbit ||
                        this.grid.getItem(xCoordinate, i) instanceof Beaver) {
                    consumerResults[i] = i;
                }
            }
        } else {
            for(int i = this.grid.getWidth(); i > this.yCoordinate; i--) {
                if(this.grid.getItem(xCoordinate, i) instanceof Rabbit ||
                        this.grid.getItem(xCoordinate, i) instanceof Beaver) {
                    consumerResults[i] = i;
                }
            }
        }


//        for(int i = 0; i < this.grid.getWidth(); i++) {
//            if(this.grid.getItem(xCoordinate, i) instanceof Rabbit ||
//                    this.grid.getItem(xCoordinate, i) instanceof Beaver) {
//                return i;
//            }
//        }
//        return -1;
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
    public String toString() { return ("      HT      "); }
}
