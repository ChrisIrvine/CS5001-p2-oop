public class Farmer extends AbstractItem {



    public Farmer() {

    }

    @Override
    public void process(TimeStep timeStep) {

    }

    @Override
    protected int getStock() {

    }

    @Override
    protected void addToStock(int nutrition) {

    }

    @Override
    protected void reduceStock(int nutrition) {
        this.stock =- nutrition;
    }
}
