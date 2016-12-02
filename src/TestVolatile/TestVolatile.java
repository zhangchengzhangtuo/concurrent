package TestVolatile;

/**
 * Created by Administrator on 2016/11/24.
 */
public class TestVolatile {

    public static void main(String [] argv) throws Throwable{
        Test test=new Test();
        test.start();
        Thread.sleep(500);
        test.stop();
    }
}
