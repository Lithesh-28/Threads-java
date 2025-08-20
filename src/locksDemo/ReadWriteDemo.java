package locksDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteDemo {
    private int count;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock wrireLock = lock.writeLock();

    public void increment(){
        wrireLock.lock();
        try{
            count++;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }finally {
            wrireLock.unlock();
        }
    }
    public int getCount(){
        readLock.lock();
        try{
            return count;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReadWriteDemo demo = new ReadWriteDemo();

        Runnable write = ()-> {
            for (int i=0;i<10;i++) {
                demo.increment();
                System.out.println(Thread.currentThread().getName() +"---Incremented");
            }
        };
        Runnable read = () -> {
            for (int i=0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"-read-"+demo.getCount());
            }
        };

        Thread t1 = new Thread(write,"Writer");
        Thread t2 = new Thread(read,"reader-1");
        Thread t3 = new Thread(read,"reader-2");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
