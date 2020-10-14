package concurrence;

import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExcutorTest {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {

        // 使用阿里巴巴推荐的创建线程池的方式
        // 通过 ThreadPoolExecutor 构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
//        executor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("CurrentThread Name: " + Thread.currentThread().getName() + "date: " + Instant.now());
            });
        }

        ExecutorService executorService = Executors.newScheduledThreadPool(8);
//        new ArrayBlockingQueue<Integer>();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
//        lock.lockInterruptibly();

        // 终止线程池
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }

    /**
     * 打印线程池的状态
     *
     * @param threadPool 线程池对象
     */
//    public static void printThreadPoolStatus(ThreadPoolExecutor threadPool) {
//        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, createThreadFactory("print-thread-pool-status", false));
//        scheduledExecutorService.scheduleAtFixedRate(() -> {
//            log.info("=========================");
//            log.info("ThreadPool Size: [{}]", threadPool.getPoolSize());
//            log.info("Active Threads: {}", threadPool.getActiveCount());
//            log.info("Number of Tasks : {}", threadPool.getCompletedTaskCount());
//            log.info("Number of Tasks in Queue: {}", threadPool.getQueue().size());
//            log.info("=========================");
//        }, 0, 1, TimeUnit.SECONDS);
//    }
}
