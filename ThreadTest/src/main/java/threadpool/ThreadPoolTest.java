package threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 测试使用 Thread Pool
 * @Author zuo_h
 * @Date 11:36 2021/5/19
 */
public class ThreadPoolTest {
    /**
     * 使用Executors创建线程池可能会导致OOM(OutOfMemory ,内存溢出)
     * 原因：
     * Java中的BlockingQueue主要有两种实现，分别是ArrayBlockingQueue 和 LinkedBlockingQueue。
     *
     * ArrayBlockingQueue是一个用数组实现的有界阻塞队列，必须设置容量。
     *
     * LinkedBlockingQueue是一个用链表实现的有界阻塞队列，容量可以选择进行设置，不设置的话，将是一个无边界的阻塞队列，最大长度为Integer.MAX_VALUE。
     *
     * 不设置LinkedBlockingQueue的容量的话，其默认容量将会是Integer.MAX_VALUE。
     */

    private static ExecutorService unsafeUseExecutor = Executors.newFixedThreadPool(15);


    /**
     * 正确使用：
     * 1. 自己直接调用 ThreadPoolExecutor 的构造函数，给 BlockQueue 指定容量
     *          一旦提交的线程数超过当前可用线程数时，就会抛出 java.util.concurrent.RejectedExecutionException，
     *          这是因为当前线程池使用的队列是有边界队列，队列已经满了便无法继续处理新的请求
     * 2. 开源类库，如 apache 和 guava 等
     */


    private static ExecutorService safeUseExecutor1 = new ThreadPoolExecutor(10, 10,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue(10));

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    /**
     * guava
     */
    private static ExecutorService pool = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            //unsafeUseExecutor.execute(new SubThread());
            safeUseExecutor1.execute(new SubThread());
            unsafeUseExecutor.execute(new SubThread());
        }
    }

    static class SubThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                //do nothing
            }
        }
    }

}