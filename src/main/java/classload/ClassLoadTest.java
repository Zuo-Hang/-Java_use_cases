package classload;

import com.sun.java.accessibility.AccessBridge;

import java.io.DataInputStream;

/**
 * 验证 ClassLoad
 *
 * @Author zuo_h
 * @Date 9:59 2021/5/19
 */
public class ClassLoadTest {

    /**
     * 类的加载过程：加载、验证、准备、解析、初始化
     *

     - 加载：根据查找路径找到相应的 class 文件然后导入；
     - 验证：检查加载的 class 文件的正确性；
     - 准备：给类中的静态变量分配内存空间；
     - 解析：虚拟机将常量池中的符号引用替换成直接引用的过程。符号引用就理解为一个标示，而在直接引用直接指向内存中的地址；
     - 初始化：对静态变量和静态代码块执行初始化工作。
     * @param args
     */

    public static void main(String[] args) {
        //比如 String 的类加载器为空这种情况 是因为其为根类加载器(大部分JDK中类都是这样)
        //根类加载器是使用 C++ 编写的，JVM 不能够也不允许程序员获取该类，所以返回的是null
        ClassLoader classLoader1 = DataInputStream.class.getClassLoader();
        ClassLoader classLoader2 = AccessBridge.class.getClassLoader();
        ClassLoader classLoader3 = ClassLoadTest.class.getClassLoader();
        System.out.println(classLoader3.getClass().getName());
    }


    public static void main2(String[] args) {
        System.out.println("boot " + System.getProperty("sun.boot.class.path"));
        System.out.println("ext " + System.getProperty("java.ext.dirs"));
        System.out.println("system " + System.getProperty("java.class.path"));
        try {
            System.out.println(Class.forName("Property").getClassLoader() + "PPP");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
