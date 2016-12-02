package testAtomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2016/12/2.
 */
public class AtomicReferenceTest {

    public static AtomicReference<User> atomicReference=new AtomicReference<>();

    public static void main(String [] argv){
        User user=new User("zhangcheng",26);
        atomicReference.set(user);
        User updateUser=new User("xiaorange",27);
        User oldUser=atomicReference.get();
        atomicReference.compareAndSet(oldUser,updateUser);
        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getOld());


    }

    static class User{
        private String name;
        private int old;

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
