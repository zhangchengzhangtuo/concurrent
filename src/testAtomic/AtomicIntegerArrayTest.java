package testAtomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by Administrator on 2016/12/2.
 */
public class AtomicIntegerArrayTest {

    static int [] value=new int[] {1,2};
    static AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(value);

    //AtomicIntegerArray����Ҫע����ǣ�����valueͨ������������ݽ�ȥ��Ȼ��AtomicIntegerArray�Ὣ��ǰ���鸴��һ�ݣ�
    // ���Ե�AtomicIntegerArray���ڲ�������Ԫ�ؽ����޸�ʱ������Ӱ�쵽��������顣
    public static void main(String [] args){
        atomicIntegerArray.getAndSet(0,3);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(value[0]);
    }
}
