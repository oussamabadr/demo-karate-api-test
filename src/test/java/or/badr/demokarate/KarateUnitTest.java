package or.badr.demokarate;

import com.intuit.karate.junit5.Karate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class KarateUnitTest {

    @BeforeAll
    public static void setUp() {
        BackendServer.main(new String[0]);
    }

    @AfterAll
    public static void tearDown() {
        BackendServer.stopServer();
    }

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }

}
