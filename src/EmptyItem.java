public class EmptyItem extends AbstractItem {
    @Override
    public void process(TimeStep timeStep) {
        return;
    }

    @Override
    protected int getStock() {
        return 0;
    }

    @Override
    protected void addToStock(int nutrition) {
        return;
    }

    @Override
    protected void reduceStock(int nutrition) {
        return;
    }
}
