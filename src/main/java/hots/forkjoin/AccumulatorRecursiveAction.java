package hots.forkjoin;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class AccumulatorRecursiveAction extends RecursiveAction {
    private final int start;

    private final int end;

    private final int[] data;

    private final int limit = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if ((end - start) <= limit) {
            for (int i = start; i < end; i++) {
                AccumulatorHelper.accumulator(data[i]);
            }
        } else {
            int mid = (start + end) / 2;
            AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start, mid, data);
            AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid, end, data);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }

    public static class AccumulatorHelper {
        private static final AtomicInteger result = new AtomicInteger();

        private static void accumulator(int value) {
            result.getAndAdd(value);
        }

        public static int getResult() {
            return result.get();
        }

        public static void reset() {
            result.set(0);
        }
    }
}
