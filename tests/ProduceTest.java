import org.junit.Test;

import static org.junit.Assert.*;

public class ProduceTest {
    Produce corn = new Produce("Corn");
    Produce radish = new Produce("Radish");

    @Test
    public void getProduceType() {
        assertEquals(Produce.ProduceType.Corn, corn.getProduceType());
        assertEquals(Produce.ProduceType.Radish, radish.getProduceType());
    }
}