package threadsDemo;

public class Yeild extends Thread {
    @Override
    public void run() {
        for (int i = 0; i<=10; i++){
            System.out.println(Thread.currentThread().getName()+" is running");
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        Yeild t1 = new Yeild();
        Yeild t2 = new Yeild();
        t1.start();
        t2.start();

    }
}
