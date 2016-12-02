package TestVolatile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/24.
 */
public class Test{

    volatile boolean isRunning=true;
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long i=0;

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    i++;
                    System.out.println(i);
//                    System.out.println("current time:"+simpleDateFormat.format(new Date()));
//                    try{
//                        Thread.sleep(1000);
//                    }catch(Exception e){
//                        e.printStackTrace();
//                    }
                }
            }
        }).start();
    }

    public void stop(){
        isRunning=false;
//        System.out.println("stop time:" + simpleDateFormat.format(new Date()));
    }
}
