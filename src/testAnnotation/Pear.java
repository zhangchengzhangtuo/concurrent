package testAnnotation;

/**
 * Created by Administrator on 2016/11/26.
 */
public class Pear {

    @FruitName(value="Yantai pear")
    private String name;

    @FruitProvider(id=2,user="tianxiadiyili",address = "yantai")
    private FruitProvider provider;

}
