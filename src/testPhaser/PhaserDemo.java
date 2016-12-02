package testPhaser;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/12/1.
 */
public class PhaserDemo {

    public static void main(String [] argv) throws Throwable{
        testPhaserDifferentStage();
    }

    public static void testPhaser() throws Throwable{
        int parties=3;
        final int phases=4;
        final Phaser phaser=new Phaser(parties){
            @Override
            public boolean onAdvance(int phase,int registeredParties){
                System.out.println("=====Phase:"+phase+"======");
                return registeredParties==0;
            }
        };
        for(int i=0;i<parties;i++){
            final int threadId=i;
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int phase=0;phase<phases;phase++){
                        System.out.println(String.format("Thread %s,phase %s", threadId, phase));
                        phaser.arriveAndAwaitAdvance();
                    }
                }
            });
            thread.start();
        }
    }

    public static void testPhaserDifferentStage() throws Throwable{
        int parties=3;
        final int phases=4;
        final Phaser phaser=new Phaser(parties){
            @Override
            public boolean onAdvance(int phase,int registeredParties){
                System.out.println("=====Phase:"+phase+"======");
                return registeredParties==0;
            }
        };
        for(int i=0;i<parties;i++){
            final int threadId=i;
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int phase=0;phase<phases;phase++){
                        if(phase==0){
                            System.out.println(String.format("Thread %s,This is phase0,we do something in the phase0", threadId));
                        }else if(phase==1){
                            System.out.println(String.format("Thread %s,This is phase1,we do something in the phase1", threadId));
                        }else if(phase==2){
                            System.out.println(String.format("Thread %s,This is phase2,we do something in the phase2", threadId));
                        }else if(phase==3){
                            System.out.println(String.format("Thread %s,This is phase3,we do something in the phase3", threadId));
                        }
                        phaser.arriveAndAwaitAdvance();
                    }
                }
            });
            thread.start();
        }
    }

    public static void testPhaserArriveAndDeregister() throws Throwable{
        int parties=3;
        final int phases=4;
        final Phaser phaser=new Phaser(parties){
            @Override
            public boolean onAdvance(int phase,int registeredParties){
                System.out.println("=====Phase:"+phase+"======");
                return registeredParties==0;
            }
        };
        for(int i=0;i<parties;i++){
            final int threadId=i;
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int phase=0;phase<phases;phase++){
                        System.out.println(String.format("Thread %s,phase %s", threadId, phase));
                        if(threadId==0){
                            if(phase==0){
                                phaser.arriveAndDeregister();
                            }
                        }else{
                            phaser.arriveAndAwaitAdvance();
                        }
                    }
                }
            });
            thread.start();
        }
    }

    //failed example：这种分线程阻塞主线程运行场景并不适用于phaser
    public static void testPhaserCopyCountDownLatchFail() throws Throwable{
        int totalThread=3;
        long start=System.currentTimeMillis();
//        final CountDownLatch countDownLatch=new CountDownLatch(totalThread);
        final Phaser phaser=new Phaser(1);
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
                    System.out.println(String.format("%s\t%s %s", new Date(), threadName, "ended"));
//                    countDownLatch.countDown();
                }
            }).start();
        }
        System.out.println(String.format("%s\t%s", new Date(), "before countDown await"));
//        countDownLatch.await();
        phaser.awaitAdvance(phaser.getPhase());
        long stop=System.currentTimeMillis();
        System.out.println(String.format("Total time:%sms", stop - start));
    }

    //successful example：这种主线程阻塞分线程运行的场景可以使用phaser替代
    public static void testPhaserCopyCountDownLatchSuccess() throws Throwable{
//        final CountDownLatch countDownLatch=new CountDownLatch(1);
        final Phaser phaser=new Phaser(1);
        for(int i=0;i<3;i++){
            final int n=i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(new Date()+" 运动员"+n+"已经准备好了");
//                        countDownLatch.await();
                        phaser.awaitAdvance(phaser.getPhase());//(该方法等待某一阶段执行完毕)等待其他运动员准备好
                    } catch (Exception e) {
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
//        countDownLatch.countDown();
        phaser.arrive();//所有运动员准备好了
        System.out.println("砰！！");
    }
}
