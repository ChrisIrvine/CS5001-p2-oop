public class Transport extends AbstractItem {

    enum TransType {
        Vertical,
        Horizontal;
    }

    private TransType transType;

    public Transport(TransType transType) {
        this.transType = transType;
    }

    @Override
    public void process(TimeStep timeStep) {

    }

    @Override
    protected int getStock() {
        return 1;
    }

    @Override
    protected void addToStock(int nutrition) {

    }

    @Override
    protected void reduceStock(int nutrition) {

    }
}