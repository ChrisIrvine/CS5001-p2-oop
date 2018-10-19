import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeaverTest.class,
        RabbitTest.class,
        CornFarmerTest.class,
        RadishFarmerTest.class,
        HorizontalTransporterTest.class,
        VerticalTransporterTest.class,
        GridTest.class
})

public class TestSuite {

}
