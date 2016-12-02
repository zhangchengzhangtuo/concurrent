package TestLock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2016/11/24.
 */
public class ReadWriteLockDemo {

    static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public static void main(String [] argv){
        final ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                try{
                    System.out.println(simpleDateFormat.format(new Date())+" Thread1 started with read lock");
                    Thread.sleep(2000);
                    System.out.println(simpleDateFormat.format(new Date())+" Thread1 ended");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    readWriteLock.readLock().unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.readLock().lock();
                try{
                    System.out.println(simpleDateFormat.format(new Date())+" Thread2 started with read lock");
                    Thread.sleep(2000);
                    System.out.println(simpleDateFormat.format(new Date())+" Thread2 ended");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    readWriteLock.readLock().unlock();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Lock lock=readWriteLock.writeLock();
                lock.lock();
                try{
                    System.out.println(simpleDateFormat.format(new Date())+" Thread3 started with read lock");
                    Thread.sleep(2000);
                    System.out.println(simpleDateFormat.format(new Date())+" Thread3 ended");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
