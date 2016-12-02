package testAtomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by Administrator on 2016/12/2.
 */
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater=AtomicIntegerFieldUpdater.newUpdater(User.class,"old");

    public static void main(String [] argv) throws Throwable{
        User user=new User("zhangcheng",10);
        System.out.println(atomicIntegerFieldUpdater.getAndIncrement(user));
        System.out.println(atomicIntegerFieldUpdater.get(user));
    }

    static class User{
        private String name;
        public volatile int old;

        public User(String name,int old){
            this.name=name;
            this.old=old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
