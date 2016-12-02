package testAnnotation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by Administrator on 2016/11/27.
 */
public class TestPassword {


    public static void trackUseCases(List<Integer> useCases,Class<?> clazz){
        for(Method m:clazz.getDeclaredMethods()){
            UseCase us=m.getAnnotation(UseCase.class);
            if(us!=null){
                System.out.println("Found Use Case:"+us.id()+" "+us.description());
                useCases.remove(new Integer(us.id()));
            }
        }
    }
}
