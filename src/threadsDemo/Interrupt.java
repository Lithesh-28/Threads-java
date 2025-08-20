package threadsDemo;

public class Interrupt extends Thread {

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println("Thread is Running....");
        } catch (InterruptedException e) {
            System.out.println("Interrupted Thread "+e);
        }
    }

    public static void main(String[] args) {
        Interrupt m = new Interrupt();
        m.start();
        m.interrupt();
    }
}
