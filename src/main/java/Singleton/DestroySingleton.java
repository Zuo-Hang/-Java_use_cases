package Singleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 *
 * 破坏单例模式 简单单例 Singleton
 * 1.反射
 * 2.序列化  被反序列化后
 * 3.克隆
 *
 * 解决方案如下：UnableDestroySingleton
 * 1、防止反射
 *    定义一个全局变量，当第二次创建的时候抛出异常
 * 2、防止克隆破坏
 *       重写clone(),直接返回单例对象
 * 3、防止序列化破坏
 *    添加readResolve(),返回Object对象
 * @Author zuo_h
 * @Date 23:33 2021/5/18
 */
public class DestroySingleton {

    public static void main(String[] args) throws Exception {
        System.out.println("-----------序列化----------------------");
        Singleton originSingleton = Singleton.getInstance();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(Singleton.getInstance());
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        Singleton serializeSingleton = (Singleton) ois.readObject();
        System.out.println(originSingleton == serializeSingleton);
        //false

        System.out.println("-----------反射----------------------");
        //通过反射获取
        Constructor<Singleton> cons = Singleton.class.getDeclaredConstructor();
        cons.setAccessible(true);
        Singleton reflextSingleton = cons.newInstance();
        System.out.println(reflextSingleton == originSingleton);
        //false

        System.out.println("---------------------------克隆----------------------");

        Singleton cloneSingleton = (Singleton) originSingleton.clone();
        System.out.println(cloneSingleton == originSingleton);
        //false
    }


    private static class Singleton  implements Serializable,Cloneable{
        /**
         * 1.构造方法私有化，外部不能new
         */
        private Singleton() {}


        //2.本类内部创建对象实例
        private static  volatile  Singleton instance;


        //3.提供一个公有的静态方法，返回实例对象
        public static  Singleton getInstance() {
            if(instance == null) {
                synchronized (Singleton.class) {
                    if(instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

    }


    private static class UnableDestroySingleton  implements Serializable,Cloneable{

        private static volatile boolean isCreate = false;//默认是第一次创建
        /**
         * 1.构造方法私有化，外部不能new
         */
        private UnableDestroySingleton() {
            if(isCreate) {
                throw new RuntimeException("已然被实例化一次，不能在实例化");
            }
            isCreate = true;
        }


        //2.本类内部创建对象实例
        private static  volatile  Singleton instance;


        //3.提供一个公有的静态方法，返回实例对象
        public static  Singleton getInstance() {
            if(instance == null) {
                synchronized (Singleton.class) {
                    if(instance == null) {
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return instance;
        }

        /**
         * 防止序列化破环
         * @return
         */
        private Object readResolve() {
            return instance;
        }

    }
}
