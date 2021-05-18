package Singleton;

/**
 * 饿汉式是典型的空间换时间，当类装载的时候就会创建类实例，不管你用不用，
 * 先创建出来，然后每次调用的时候，就不需要判断了，节省了运行时间。
 * @Author zuo_h
 * @Date 23:24 2021/5/18
 */
public class SingletonDemo1 {
    private static SingletonDemo1 instance = new SingletonDemo1();

    // 私有化构造方法
    private SingletonDemo1() {

    }

    public static SingletonDemo1 getInstance() {
        return instance;
    }
}
