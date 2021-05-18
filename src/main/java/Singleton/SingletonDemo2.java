package Singleton;

/**
 * 单例模式的懒汉式体现了缓存的思想，延时加载就是一开始不要加载资源或者数据，
 * 一直 等，等到马上就要使用这个资源的或者数据了，躲不过去了才去加载。
 *
 * 不加同步的懒汉式是线程不安全的
 * @Author zuo_h
 * @Date 21:37 2021/5/18
 */
public class SingletonDemo2 {
    //类初始化时，不初始化这个对象(延时加载，真正用的时候再创建)
    private static SingletonDemo2 instance;

    //构造器私有化
    private SingletonDemo2() {
    }

    //方法同步，调用效率低
    public static SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }
}
