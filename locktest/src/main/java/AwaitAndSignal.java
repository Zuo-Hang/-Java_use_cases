import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *       Lock
 *       /  \
 *      /    \
 * await ---- signal
 *
 * 测试 Condition 的功能  需要依赖锁块，不支持先唤醒再阻塞
 *
 * @Author zuo_h
 * @Date 21:56 2021/5/19
 */
public class AwaitAndSignal {

    /**
     * 创建的时候可以传入 true/false 来指定公平或非公平
     */
    static Lock lock=new ReentrantLock();
    static Condition condition=lock.newCondition();

    public static void main(String[] args) {
        new Thread(()->{
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"------come in");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"-------被唤醒");
            }finally {
                lock.unlock();
            }

        },"thread1").start();

        new Thread(()->{
            lock.lock();
            try{
                condition.signal();
                System.out.println(Thread.currentThread().getName()+"-------通知");
            }finally {
                lock.unlock();
            }

        },"thread2").start();
    }
}
