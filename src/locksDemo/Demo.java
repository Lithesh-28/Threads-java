package locksDemo;
class MyThread{
    private int balance = 1000;

    public synchronized void withdrawAmount(int amount){
        System.out.println(Thread.currentThread().getName()+"--Attempting to withdraw");
        if (balance > amount){
            balance-=amount;

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"--Withdrawal complete Remaining balance "+balance);
        }
    }
}
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        MyThread m = new MyThread();
        Runnable ob1 =()->{
           m.withdrawAmount(50);
        };
        Thread t1 = new Thread(ob1,"thread1");
        Thread t2 = new Thread(ob1,"thread2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
