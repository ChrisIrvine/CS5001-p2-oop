public class Farmer extends AbstractItem {

    enum FarmerType {
        CornFarmer (5, new Produce("Corn"), 4, 1, 2, 1, 2),
        RadishFarmer (10, new Produce("Radish"), 3, 1, 1, 1, 1);

        private int production;
        private Produce produce;
        private int delay;
        private int stock;
        private int[] deny;

        FarmerType(int production, Produce produce, int delay, int... deny) {
            this.production = production;
            this.produce = produce;
            this.delay = delay;
            this.stock = 0;
            this.deny = deny;
        }

        int getProduction() {
            return this.production;
        }

        Produce getProduce() {
            return this.produce;
        }

        int getDelay() {
            return this.delay;
        }

        int getStock() {
            return this.stock;
        }

        int[] getDeny() { return this.deny; }

        void increaseStock(int increase) {
            this.stock += increase;
        }

        void decreaseStock(int decrease) {
            if ((this.stock -= decrease) < 0) {
                this.stock = 0;
            } else {
                this.stock -= decrease;
            }
        }
    }
    FarmerType farmerType;

    public Farmer(FarmerType farmerType, int x, int y) {
        this.farmerType = farmerType;
        this.xCoordinate = 
    }

    @Override
    public void process(TimeStep timeStep) {
        if(timeStep.getValue()%this.farmerType.getDelay() == 0) {
            for (int i = 0; i < this.farmerType.getDeny().length; i++) {
                if ((this.xCoordinate + this.farmerType.getDeny()[i]) < 0
                        || (this.xCoordinate + this.farmerType.getDeny()[i])
                        >= grid.getHeight()
                        || (this.yCoordinate + this.farmerType.getDeny()[i])
                        < 0
                        || (this.yCoordinate + this.farmerType.getDeny()[i])
                        >= grid.getWidth()) {
                    return;
                } else if (i % 2 == 0) {
                    if (grid.getItem(this.xCoordinate +
                                    this.farmerType.getDeny()[i],
                            this.yCoordinate) instanceof Farmer) {
                        return;
                    }
                } else if (i % 2 != 0) {
                    if (grid.getItem(this.xCoordinate, this.yCoordinate
                            + this.farmerType.getDeny()[i]) instanceof Farmer) {
                        return;
                    }
                } else {
                    this.farmerType.increaseStock(this.farmerType.getProduce()
                            .getProduceType().getNutritionValue() *
                            this.farmerType.getProduction());
                }
            }
        }
    }

    @Override
    protected int getStock() {
        return this.farmerType.getStock();
    }

    @Override
    protected void addToStock(int nutrition) {
        this.farmerType.increaseStock( nutrition);
    }

    @Override
    protected void reduceStock(int nutrition) {
        this.farmerType.decreaseStock(nutrition);
    }

    public FarmerType getType() {
        return this.farmerType;
    }
}
