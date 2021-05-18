package Singleton;

/**
 * 静态内部类的单例模式
 * 当getInstance方法第一次被调用的时候,它第一次读取SingletonHolder.instance，
 * 导致SingletonHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静态域，
 * 从而创建Singleton的实例，
 * 由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。
 * @Author zuo_h
 * @Date 21:41 2021/5/18
 */

public class SingletonDemo3 {
    private static class SingletonClassInstance {
        private static final SingletonDemo3 instance = new SingletonDemo3();
    }

    private SingletonDemo3() {
    }

    public static SingletonDemo3 getInstance() {
        return SingletonClassInstance.instance;
    }

}