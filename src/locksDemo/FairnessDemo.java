package locksDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessDemo {
    private final Lock fairLock =  new ReentrantLock();

    public void accessResource(){
        fairLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "--Accessed the resource");
            Thread.sleep(1000);
        }catch(Exception e){
            Thread.currentThread().interrupt();
        }finally {
            System.out.println(Thread.currentThread().getName()+"--Released the resource");
            fairLock.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessDemo demo = new FairnessDemo();
        Runnable obj = () ->
            demo.accessResource();
        Thread t1 = new Thread(obj,"thread-1");
        Thread t2 = new Thread(obj,"thread-2");
        Thread t3 = new Thread(obj,"thread-3");
        t1.start();
        t2.start();
        t3.start();
    }
}
