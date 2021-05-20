import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AbstractQueuedSynchronizer 抽象队列同步器
 * 通过内置一个 FIFO 的队列来完成资源获取线程的排队工作，并通过一个 int 变量表示持有锁的状态
 * 模板模式
 * @Author zuo_h
 * @Date 22:40 2021/5/19
 */
public class AQSDemo {

    /**
     *          资源(state)  --volatile int
     *
     *      +------+  prev +-----+       +-----+
     *      |thread| <---- |     | <---- |     |
     * head |      | ----> | node| ----> |     | tail
     *      +------+       +-----+ next  +-----+            node 中有一个 waitState
     *
     * AQS 为线程实现了一个 排队等候机制 ，如果共享资源被占用，就需要一定的阻塞等待机制来保证锁的分配
     * 这个机制主要是由 CLH 队列的变体(改为双向)实现。将暂时获取不到锁的线程加入到队列中，这个队列就是
     * AQS 的抽象表现。它将请求共享资源的线程封装成节点（node），通过 CAS 、自旋以及LockSupport.park()
     * 的方式，维护state变量的状态，使并发达到同步的控制效果。
     *
     */

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+" come in");

                try {
                    TimeUnit.SECONDS.sleep(3L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }finally {
                lock.unlock();
            }
        },"thread1");

        new Thread(()->{
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+" come in");

            }finally {
                lock.unlock();
            }
        },"thread2");

        new Thread(()->{
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+" come in");

            }finally {
                lock.unlock();
            }
        },"thread3");

    }
}
