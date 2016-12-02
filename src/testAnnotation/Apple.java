package testAnnotation;

/**
 * Created by Administrator on 2016/11/26.
 */
public class Apple {

    @FruitName(value="Fushi Apple")
    private String fruitName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String fruitColor;

    @FruitProvider(id=1,user="Tom",address="China")
    private FruitProvider provider;

}
