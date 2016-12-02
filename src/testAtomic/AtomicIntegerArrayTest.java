package testAtomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Administrator on 2016/12/2.
 */
public class AtomicIntegerArrayTest {

    static int [] value=new int[] {1,2};
    static AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(value);

    //AtomicIntegerArray类需要注意的是，数组value通过构造防范传递进去，然后AtomicIntegerArray会将当前数组复制一份，
    // 所以当AtomicIntegerArray对内部的数组元素进行修改时，不会影响到传入的数组。
    public static void main(String [] args){
        atomicIntegerArray.getAndSet(0,3);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(value[0]);
    }
}
