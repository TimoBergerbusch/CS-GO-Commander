import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

public class ConfigTest {


    @BeforeTest
    public static void ConfigTest() {
//        ConfigTest test = new ConfigTest();
//        test.readFileTest();

    }

    public ConfigTest() {
    }

    @Test
    public void parseCommandsTest() {
        Assertions.assertEquals("mp_freezetime 1;", Config.readCommand("mp_freezetime 1;").getCommand());
    }
}