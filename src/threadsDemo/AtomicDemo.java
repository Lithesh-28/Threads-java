package threadsDemo;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment(){
        counter.incrementAndGet();
    }

    public int getCounter(){
        return counter.get();
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicDemo ac = new AtomicDemo();

        Thread t1 = new Thread(()->{
           for (int i=0;i<10000;i++){
               ac.increment();
           }
        });
        Thread t2 = new Thread(()->{
           for (int i=0;i<10000;i++){
               ac.increment();
           }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(ac.getCounter());
    }
}
