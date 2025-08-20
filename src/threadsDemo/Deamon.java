package threadsDemo;

public class Deamon extends Thread {
    @Override
    public void run() {
        while (true){
            System.out.println("hello World");
        }
    }

    public static void main(String[] args) {
        Deamon t1 = new Deamon();
        t1.setDaemon(true);
        t1.start();
        System.out.println("Main done!");

    }
}
