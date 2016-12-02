package testCyclicBarrier;

import java.util.Date;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CyclicBarrierDemo {

    public static void main(String [] argv){
        int totalThread=3;
        final CyclicBarrier cyclicBarrier=new CyclicBarrier(totalThread);
        for(int i=0;i<totalThread;i++){
            final String threadName="Thread1"+i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, " is waiting"));
                    try{
                        cyclicBarrier.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
                }
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i=0;i<totalThread;i++){
            final String threadName="Thread2"+i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, " is waiting"));
                    try{
                        cyclicBarrier.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
                }
            }).start();
        }

    }
}
