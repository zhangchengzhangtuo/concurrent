package testAnnotation;

/**
 * Created by Administrator on 2016/11/26.
 */
public class Banana {

    @FruitName(value="Big banana")
    private String fruitName;

    @FruitColor(fruitColor=FruitColor.Color.YELLOW)
    private String fruitColor;

}
