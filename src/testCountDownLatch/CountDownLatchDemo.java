package testCountDownLatch;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/12/1.
 */
public class CountDownLatchDemo {

    public static void main(String [] argv) throws Throwable{
        testCountDownLatch2();
    }

    //分线程阻塞主线程运行，所有的分线程运行完了之后，主线程开始运行
    public static void testCountDownLatch1() throws Throwable{
        int totalThread=3;
        long start=System.currentTimeMillis();
        final CountDownLatch countDownLatch=new CountDownLatch(totalThread);
        for(int i=0;i<totalThread;i++){
            final String threadName="Thread "+i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "started"));
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    System.out.println(String.format("%s\t%s %s", new Date(),threadName,"ended"));
                    countDownLatch.countDown();
                    System.out.println(String.format("remained thread:%s",countDownLatch.getCount()));
                }
            }).start();
        }
        System.out.println(String.format("%s\t%s", new Date(),"before countDown await"));
        countDownLatch.await();
        long stop=System.currentTimeMillis();
        System.out.println(String.format("Total time:%sms", stop - start));
    }

    //主线程阻塞分线程运行，主线程释放之后分线程得以运行
    public static void testCountDownLatch2() throws Throwable{
        final CountDownLatch countDownLatch=new CountDownLatch(1);
        for(int i=0;i<3;i++){
            final int n=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(new Date()+" 运动员"+n+"已经准备好了");
                        countDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(new Date()+" 运动员"+n+"开始跑起来了");
                }
            }).start();
        }

        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("预备！！");
        countDownLatch.countDown();
        System.out.println("砰！！");
    }
}
