package executorsDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ExecutorDemo {
    public static void main(String[] args) {

        ExecutorService executors = Executors.newFixedThreadPool(3);
        for (int i=1;i<10;i++) {
            int finalI = i;
            executors.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);
            });
        }
        executors.shutdown();

    }
    public static long factorial(int n){

        long result = 1;
        for (int i=1; i<=n;i++){
            result*=i;
        }
        return result;
    }

}
