package TestAwait;

import sun.reflect.annotation.ExceptionProxy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/11/24.
 */
public class ConditionTest {

    static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String [] args) throws Throwable{
        final Lock lock=new ReentrantLock(true);
        final Condition condition=lock.newCondition();
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(simpleDateFormat.format(new Date())+" Thread1 is waiting");
                    long waitTime=condition.awaitNanos(TimeUnit.SECONDS.toNanos(5));
                    System.out.println(simpleDateFormat.format(new Date())+" Thread1 remaining time "+waitTime);
                    System.out.println(simpleDateFormat.format(new Date())+" Thread1 is waken up");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();

        Thread.sleep(10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    System.out.println(simpleDateFormat.format(new Date())+" Thread2 is running");
                    Thread.sleep(4000);
                    condition.signal();
                    System.out.println(simpleDateFormat.format(new Date())+" Thread2 ended");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();

    }
}
