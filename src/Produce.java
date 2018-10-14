public class Produce {

    enum ProduceType {
        Corn (5),
        Radish (1);

        private int nutritionValue;

        ProduceType(int nutritionValue) {
            this.nutritionValue = nutritionValue;
        }

        int getNutritionValue() {
            return this.nutritionValue;
        }
    }

    ProduceType produceType;

    public Produce(String produceType) {
        this.produceType = ProduceType.valueOf(produceType);
    }

    public ProduceType getProduceType() {
        return produceType;
    }
}
