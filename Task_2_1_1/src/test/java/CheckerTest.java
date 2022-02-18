import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.sartakov.Checker;
import ru.nsu.sartakov.SimpleChecker;
import ru.nsu.sartakov.StreamChecker;
import ru.nsu.sartakov.ThreadChecker;

public class CheckerTest {

    private final long[] testArray = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
    private long[] bigArray = new long[1000000];

    // will have prime
    public void buildHugeArray() {
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = testArray[i % testArray.length];
        }
    }

    @Test
    public void simpleTest() {
        Assertions.assertFalse(SimpleChecker.sequentRun(testArray));
    }

    @Test
    public void sequentialTest() {
        buildHugeArray();
        Assertions.assertFalse(SimpleChecker.sequentRun(bigArray));
    }

    @Test
    public void streamTest() {
        buildHugeArray();
        int index = 0;
        Long[] testArrayLong = new Long[bigArray.length];
        for (final Long value : bigArray) {
            testArrayLong[index++] = value;
        }
        Assertions.assertFalse(StreamChecker.streamRun(testArrayLong));
    }

    @Test
    public void threadTest2() throws InterruptedException {
        buildHugeArray();
        Assertions.assertFalse(ThreadChecker.threadRun(bigArray, 2));
    }

    @Test
    public void threadTest4() throws InterruptedException {
        buildHugeArray();
        Assertions.assertFalse(ThreadChecker.threadRun(bigArray, 4));
    }

    @Test
    public void threadTest8() throws InterruptedException {
        buildHugeArray();
        Assertions.assertFalse(ThreadChecker.threadRun(bigArray, 8));
    }

    @Test
    public void threadTest16() throws InterruptedException {
        buildHugeArray();
        Assertions.assertFalse(ThreadChecker.threadRun(bigArray, 16));
    }
}
