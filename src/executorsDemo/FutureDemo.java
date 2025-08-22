package executorsDemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService execute = Executors.newSingleThreadExecutor();
        Future<?> future = execute.submit(() -> {
            System.out.println("hello");
            return "hiii";
        });
        System.out.println(future.get());
        if (future.isDone())
            System.out.println("Task is done !!");
        execute.shutdown();
    }
}
