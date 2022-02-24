import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.SimpleChecker;
import ru.nsu.sartakov.StreamChecker;
import ru.nsu.sartakov.ThreadChecker;

public class CheckerTest {

    private final long[] testArray = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
    private final long[] bigArray = new long[1000000];

    public void buildHugeArray() {
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = testArray[i % testArray.length];
        }
    }

    @BeforeEach
    public void init() {
        buildHugeArray();
    }

    @Test
    public void simpleTest() {
        Assertions.assertFalse(SimpleChecker.sequentRun(testArray));
    }

    @Test
    public void sequentialTest() {
        Assertions.assertFalse(SimpleChecker.sequentRun(bigArray));
    }

    @Test
    public void streamTest() {
        int index = 0;
        Long[] testArrayLong = new Long[bigArray.length];
        for (final Long value : bigArray) {
            testArrayLong[index++] = value;
        }
        Assertions.assertFalse(StreamChecker.streamRun(testArrayLong));
    }

    @Test
    public void threadTest2() throws InterruptedException {
        ThreadChecker thread = new ThreadChecker();
        Assertions.assertFalse(thread.threadRun(bigArray, 2));
    }

    @Test
    public void threadTest4() throws InterruptedException {
        ThreadChecker thread = new ThreadChecker();
        Assertions.assertFalse(thread.threadRun(bigArray, 4));
    }

    @Test
    public void threadTest8() throws InterruptedException {
        ThreadChecker thread = new ThreadChecker();
        Assertions.assertFalse(thread.threadRun(bigArray, 8));
    }

    @Test
    public void threadTest16() throws InterruptedException {
        ThreadChecker thread = new ThreadChecker();
        Assertions.assertFalse(thread.threadRun(bigArray, 16));
    }
}
