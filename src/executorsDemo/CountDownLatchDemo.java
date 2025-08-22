package executorsDemo;

import java.util.concurrent.*;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int numberOfServices = 3;

        ExecutorService executor = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);
        executor.submit(new Demo(latch));
        executor.submit(new Demo(latch));
        executor.submit(new Demo(latch));

        latch.await(3, TimeUnit.SECONDS);
        System.out.println("main");
        executor.shutdown();


    }

    static class Demo implements Callable<String> {
        private final CountDownLatch latch;

        Demo(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public String call() throws Exception {
            try {
                Thread.sleep(4000);
                System.out.println(Thread.currentThread().getName() + "--");

            } catch (Exception e) {
            } finally {
                latch.countDown();
            }
            return "hello world !";
        }
    }
}
