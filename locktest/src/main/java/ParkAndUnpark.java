import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 * 优势：LockSupport
 * 1.不需要锁块
 * 2.unpark（） 可以在 park（） 前执行，依然可以唤醒
 * @Author zuo_h
 * @Date 22:07 2021/5/19
 */
public class ParkAndUnpark {

    /**
     * LockSupport 是用来创建锁和其他同步类的基本线程阻塞原语
     * LockSupport 是一个线程阻塞工具类，所有的方法都是静态的，可以让线程在任意位置进行阻塞，阻塞后也有对应的唤醒方法
     * 归根结底：LockSupport调用的是 Unsafe 类中的本地方法
     * LockSupport 和每个使用它的线程都有一个许可（permit）关联。permit相当于1，0的开关，默认为0
     * 调用一次 unpark 就0+1改为1，调用一次 park 就0-1=-1
     * 当 permit 小于1时阻塞该线程，每个线程的 permit 只有一个，重复调用 unpark 不会积累凭证（最多持有一个凭证），
     *       但是 park （凭证需求）可以累计。
     * @param args
     */
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"---------come in");
            //阻塞当前线程
            LockSupport.park();
            System.out.println(Thread.currentThread().getName()+"---------被唤醒");
        }, "thread1");
        thread1.start();

        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            LockSupport.unpark(thread1);
            System.out.println(Thread.currentThread().getName()+"-------------发送信号");
        },"thread2").start();
    }
}
