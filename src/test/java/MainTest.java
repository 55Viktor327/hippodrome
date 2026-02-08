import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Test
    @Timeout(value=22)
//    @Disabled
    void main_executionTimeDoesNotExceed22Seconds() throws Exception {
        Main.main(new String[0]);
    }
}
