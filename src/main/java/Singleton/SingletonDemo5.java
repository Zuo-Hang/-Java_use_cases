package Singleton;

/**
 * 线程安全的懒汉式（双重检查加锁）：
 * @Author zuo_h
 * @Date 21:39 2021/5/18
 */
public class SingletonDemo5 {
    private volatile static SingletonDemo5 SingletonDemo5;

    private SingletonDemo5() {
    }

    public static SingletonDemo5 newInstance() {
        if (SingletonDemo5 == null) {
            synchronized (SingletonDemo5.class) {
                if (SingletonDemo5 == null) {
                    SingletonDemo5 = new SingletonDemo5();
                }
            }
        }
        return SingletonDemo5;
    }
}
