package locksDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class withdraw{
    private int balance = 10000;

    private final Lock lock = new ReentrantLock();

    public void withdrawAmount(int amount){
        System.out.println(Thread.currentThread().getName()+"--Attempting to withdraw");
        try{
            if(lock.tryLock(1000, TimeUnit.MILLISECONDS)){
                try{
                    if (balance > amount){
                        balance -= amount;
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName()+"--Withdrawal complete "+balance);
                    }else {
                        System.out.println("Insufficient balance");
                    }
                }catch(Exception e){
                    Thread.currentThread().interrupt();
                }
                finally {
                    lock.unlock();
                }
            }else
                System.out.println(Thread.currentThread().getName()+"--could not acquire the lock");
        }catch(Exception e){
            Thread.currentThread().interrupt();
        }
        if(Thread.currentThread().isInterrupted()){
            System.out.println();
        }
    }
}

public class TryLockDemo {
    public static void main(String[] args) {
        withdraw w = new withdraw();
        Runnable ob = () ->
          w.withdrawAmount(50);

        Thread t1 = new Thread(ob);
        Thread t2 = new Thread(ob);
        t1.start();
        t2.start();

    }
}
