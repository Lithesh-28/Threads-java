package threadsDemo;

import java.io.Writer;
class SharedObj{
    volatile boolean flag = false;


    public void setFlagTrue(){
        flag = true;
        System.out.println("Writer Thread made the flag true ");
    }

    public void readFlag(){
        while(!flag){
            //do nothing
        }
        System.out.println("Flag is true");
    }
}

public class VolatileDemo {


    public static void main(String[] args) {
        SharedObj obj = new SharedObj();
        Thread writer = new Thread(()->{
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                Thread.currentThread().interrupt();
            }
             obj.setFlagTrue();
        });
        Thread reader = new Thread(()->{
           obj.readFlag();
        });

        writer.start();
        reader.start();
    }
}
