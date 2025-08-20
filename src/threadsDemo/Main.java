package threadsDemo;

public class Main extends Thread{
    int i = 0;
    String name;
    public Main(String name){
        super(name);
    }
    @Override
    public void run() {
            for (i = 0; i < 5; i++) {

                System.out.println(Thread.currentThread().getName() + "-priority-" + Thread.currentThread().getPriority() + "-count-" + i);
            }
        try {
            Thread.sleep(1000);
        }
         catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Main l = new Main("lithesh");
        Main m = new Main("pavan");
        Main h = new Main("chiru");
        l.setPriority(Thread.MIN_PRIORITY);
        m.setPriority(Thread.NORM_PRIORITY);
        h.setPriority(Thread.MAX_PRIORITY);
        l.start();
        m.start();
        h.start();

    }
}
