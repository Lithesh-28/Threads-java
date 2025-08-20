package locksDemo;

class Resource{
    public String name;
    public Resource(String name){
        this.name=name;
    }
}

public class DeadLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Resource r1 = new Resource("Resource-1");
        Resource r2 = new Resource("Resource-2");

        Thread t1 = new Thread(()->{
           synchronized (r1){
               System.out.println(Thread.currentThread().getName()+"--has acquired--"+r1.name);
           }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (r2){
                System.out.println(Thread.currentThread().getName()+"--trying to get--"+r2.name);
            }
        });
        Thread t2 = new Thread(()->{
           synchronized (r2){
               System.out.println(Thread.currentThread().getName()+"--has acquired--"+r2.name);
           }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (r1){
                System.out.println(Thread.currentThread().getName()+"--trying to get--"+r1.name);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
