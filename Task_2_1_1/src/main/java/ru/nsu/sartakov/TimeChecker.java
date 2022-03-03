package ru.nsu.sartakov;

import java.util.concurrent.ExecutionException;

public class TimeChecker {
    /**
     * This class is time checking thing
     * All the methods are running 50 times
     * Then the average time is presented
     *
     * @param args - not needed here
     * @throws InterruptedException for interruption while thread checking
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime;
        long endTime;
        boolean result = false;
        final long[] testArray = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};
        long[] bigArray = new long[1000000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = testArray[i % testArray.length];
        }

        System.out.println("Testing sequent running:");
        startTime = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            result = SimpleChecker.sequentRun(bigArray);
        }
        endTime = System.nanoTime();
        System.out.println("The time elapsed: " + (endTime - startTime) / 50 + " nanoseconds\n");


        System.out.println("Testing parallel running");
        Long[] testArrayLong = new Long[bigArray.length];
        for(int index = 0; index < bigArray.length; index++) {
            testArrayLong[index] = bigArray[index];
        }
        startTime = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            StreamChecker.streamRun(testArrayLong);
        }
        endTime = System.nanoTime();
        System.out.println("The time elapsed: " + (endTime - startTime) / 50 + " nanoseconds\n");


        ThreadChecker thread = new ThreadChecker();

        System.out.println("Testing 2 thread running:");
        startTime = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            result = thread.threadRun(bigArray, 2);
        }
        endTime = System.nanoTime();
        System.out.println("The time elapsed: " + (endTime - startTime) / 50 + " nanoseconds\n");


        System.out.println("Testing 4 thread running:");
        startTime = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            result = thread.threadRun(bigArray, 4);
        }
        endTime = System.nanoTime();
        System.out.println("The time elapsed: " + (endTime - startTime) / 50 + " nanoseconds\n");

        System.out.println("Testing 8 thread running:");
        startTime = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            result = thread.threadRun(bigArray, 8);
        }
        endTime = System.nanoTime();
        System.out.println("The time elapsed: " + (endTime - startTime) / 50 + " nanoseconds\n");

        System.out.println("Testing 16 thread running:");
        startTime = System.nanoTime();
        for (int i = 0; i < 50; i++) {
            result = thread.threadRun(bigArray, 16);
        }
        endTime = System.nanoTime();
        System.out.println("The time elapsed: " + (endTime - startTime) / 50 + " nanoseconds\n");
        System.out.println(result);
    }
}
