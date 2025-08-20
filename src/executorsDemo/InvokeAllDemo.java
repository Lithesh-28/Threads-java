package executorsDemo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class InvokeAllDemo {
    public static void main(String[] args)  {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Callable<Integer> callable1 = () -> {
            Thread.sleep(1000);
            System.out.println("task 1");
            return 1;
        };
        Callable<Integer> callable2 = () ->{
            Thread.sleep(1000);
            System.out.println("task 2");
            return 2;
        };
        Callable<Integer> callable3 = () ->{
            Thread.sleep(1000);
            System.out.println("task 1");
            return 3;
        };


        List<Callable<Integer>> list = Arrays.asList(callable1,callable2,callable3);

        List<Future<Integer>> futures;
        try {
            futures = executor.invokeAll(list,1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Future<Integer> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {

            }catch (CancellationException e){

            }catch (ExecutionException e) {

            }
        }

        executor.shutdown();
        System.out.println("Hello world");
    }
}
