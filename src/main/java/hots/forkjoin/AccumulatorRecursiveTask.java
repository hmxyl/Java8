package hots.forkjoin;

import java.util.concurrent.RecursiveTask;

public class AccumulatorRecursiveTask extends RecursiveTask<Integer> {

    private final int start;

    private final int end;

    private final int[] data;

    private final int limit = 3;

    public AccumulatorRecursiveTask(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }


    @Override
    protected Integer compute() {

        if ((end - start) <= limit) {
            int result = 0;
            for (int i = start; i < end; i++) {
                result += data[i];
            }
            return result;
        } else {
            int mid = (start + end) / 2;
            AccumulatorRecursiveTask left = new AccumulatorRecursiveTask(start, mid , data);
            AccumulatorRecursiveTask right = new AccumulatorRecursiveTask(mid, end, data);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }


}
