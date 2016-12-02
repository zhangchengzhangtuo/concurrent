package TestWait;

import sun.org.mozilla.javascript.internal.Synchronizer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/24.
 */
public class Wait {

    static SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String [] argv){
        Thread thread1=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(Wait.class){
                    try{
                        System.out.println(simpleDateFormat.format(new Date())+" Thread1 is running");
                        Wait.class.wait();
                        //��ʹ�߳�2�Ѿ�notify�ˣ���������߳�2��û���ͷ����Ļ����߳�1��Ȼ���������ֱ���ȵ���Ϊֹ
                        System.out.println(simpleDateFormat.format(new Date())+" Thread1 ended");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

        Thread thread2=new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (Wait.class){
                    try{
                        System.out.println(simpleDateFormat.format(new Date())+" Thread2 is running");
                        Wait.class.notify();
                        System.out.println(simpleDateFormat.format(new Date())+" Thread2 notify");
                        for(long i=0;i<200000;i++){
                            for(long j=0;j<100000;j++){

                            }
                        }
                        System.out.println(simpleDateFormat.format(new Date())+" Thread2 release lock");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                for(long i=0;i<200000;i++){
                    for(long j=0;j<100000;j++){

                    }
                }

                System.out.println(simpleDateFormat.format(new Date())+"Thread2 ended");
            }
        });

        for(long i=0;i<200000;i++){
            for(long j=0;j<100000;j++){

            }
        }
        thread2.start();
    }
}
