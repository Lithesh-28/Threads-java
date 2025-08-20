package locksDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantDemo {
    private Lock lock = new ReentrantLock();

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner method");
            outerMethod();
        } finally {
            lock.unlock();
        }
    }
    public void outerMethod(){
        lock.lock();
        try{
            System.out.println("outer method");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantDemo demo = new ReentrantDemo();
        demo.innerMethod();
    }
}
