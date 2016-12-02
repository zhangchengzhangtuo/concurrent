package testAnnotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/26.
 */
public class TestFruit {

    public static void getFruitInfo(Object object){
        Class<?> clazz=object.getClass();
        Field[] fields=clazz.getDeclaredFields();

        for(Field field:fields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName name = field.getAnnotation(FruitName.class);
                System.out.print("Fruit Name:" + name.value());
            }
            if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor color = field.getAnnotation(FruitColor.class);
                System.out.print(",Fruit Color:" + color.fruitColor());
            }
            if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider provider = field.getAnnotation(FruitProvider.class);
                System.out.print(",Fruit FruitProvider: ProviderID:" + provider.id() + " Provider:" + provider.user() + " ProviderAddress:" + provider.address());
            }
        }

        System.out.println("");
    }

    public static void main(String [] args){
        List<Object> fruitDish=new ArrayList<>();
        Apple apple=new Apple();
        Banana banana=new Banana();
        Pear pear=new Pear();
        fruitDish.add(apple);
        fruitDish.add(banana);
        fruitDish.add(pear);

        System.out.println("now there are some fruit in the dish:");
        for(int i=0;i<fruitDish.size();i++){
            getFruitInfo(fruitDish.get(i));
        }
    }
}
