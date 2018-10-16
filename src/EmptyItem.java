public class EmptyItem extends AbstractItem {
    public int stock;

    @Override
    public void process(TimeStep timeStep) {
        return;
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

        this.stock += nutrition;
    }

    @Override
    protected void reduceStock(int nutrition) {
        if (nutrition < 0 ) {
            nutrition = Math.abs(nutrition);
        }

        this.stock -= nutrition;
    }

    @Override
    public String toString() {
        return ("              ");
    }
}
