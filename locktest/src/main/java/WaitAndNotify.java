/**
 *   synchronized
 *       /\
 *      /  \
 * wait/____\notify
 *  需要依赖锁块，不支持先唤醒再阻塞
 * @Author zuo_h
 * @Date 21:39 2021/5/19
 */
public class WaitAndNotify {

    /**
     * synchronized 被 monitor 来实现，其实 wait 和 notify 也依赖与 monitor
     *
     * 并且必须先 wait 再 notify，成对出现
     */

    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "--------------come in");
                try {
                    //wait 和 notify 必须在同步代码块中使用
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-----------被唤醒");
            }
        }, "thread1").start();

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "-----------通知");
            }
        }, "thread2").start();
    }
}
