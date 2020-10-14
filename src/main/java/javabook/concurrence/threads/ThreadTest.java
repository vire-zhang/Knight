package javabook.concurrence.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class ThreadTest {

    public static final int DELAY = 10;
    public static final int STEPS = 100;
    public static final double MAX_AMOUNT = 1000;
    public static final int NACCOUNTS = 10;
    public static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {

        var bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true){
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) { }
            };
            var t = new Thread(r);
            t.start();
        }
        long id = nextNumber.incrementAndGet();
    }

    public static AtomicLong nextNumber = new AtomicLong(15);
    public static AtomicLong largest = new AtomicLong();

    public void Test() {
        // 原子性
        long id = nextNumber.incrementAndGet();
//        largest.updateAndGet(x -> Math.max(x, observed));
//        largest.accumulateAndGet(observed, Math::max);

        // 大量线程访问相同原子值，只有当所有工作都完成之后才需要总和的值，使用如下方法更高效
        // 自增
        var adder = new LongAdder();
        adder.increment();
        adder.sum();
        // 任意累加
        var adder2 = new LongAccumulator(Long::max, 0);
        adder2.accumulate(4);
        adder2.get();

        // 线程局部变量
        String dateStamp = dateFormat.get().format(new Date());
        int random = ThreadLocalRandom.current().nextInt(100);

        // 阻塞队列
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        LinkedBlockingDeque linkedBlockingDeque = new LinkedBlockingDeque();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(100);
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        DelayQueue delayQueue = new DelayQueue();
        TransferQueue transferQueue = new LinkedTransferQueue();

        // 高效的映射、集和队列
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        ConcurrentSkipListSet<Integer> skipListSet = new ConcurrentSkipListSet<>();
        ConcurrentLinkedQueue<Integer> linkedQueue = new ConcurrentLinkedQueue<>();
    }

    // 线程局部变量：为各个线程提供各自的实例
    public static final ThreadLocal<SimpleDateFormat> dateFormat
            = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
}
