package concurrence;

public class ThreadGroupTest {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("thread当前线程组名字：" +
                    Thread.currentThread().getThreadGroup().getName());
            System.out.println("thread线程名字：" +
                    Thread.currentThread().getName());
        });

        thread.start();
        System.out.println("执行main方法线程名字：" +
                Thread.currentThread().getName());
        System.out.println("执行main方法线程组名字：" +
                Thread.currentThread().getThreadGroup().getName());

        // 复制一个线程数组到一个线程组
        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        /** ... */
        Thread[] threads = new Thread[threadGroup.activeCount()];
        ThreadGroup threadGroup2 = new ThreadGroup("threadGroup2");
        threadGroup2.enumerate(threads);

        /* 线程组统一处理异常 */
        ThreadGroup threadGroup1 = new ThreadGroup("group1") {
            // 继承ThreadGroup并重新定义以下方法
            // 在线程成员抛出unchecked exception
            // 会执行此方法
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + ": " + e.getMessage());
            }
        };

        // 这个线程是threadGroup1的一员
        Thread thread1 = new Thread(threadGroup1, new Runnable() {
            public void run() {
                // 抛出unchecked异常
                throw new RuntimeException("测试异常");
            }
        });

        System.out.println(thread1.getState());
        thread1.start();
        System.out.println(thread1.getState());
    }
}
