package concurrence;

import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BiasLockTest {

    private static PrintStream out = System.out;

    public static void main(String[] args) throws Exception {
        //延时产生可偏向对象
        Thread.sleep(5000);

        //创造100个偏向线程t1的偏向锁
        List<A> listA = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i <100 ; i++) {
                A a = new A();
                synchronized (a){
                    listA.add(a);
                }
            }
            try {
                //为了防止JVM线程复用，在创建完对象后，保持线程t1状态为存活
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();

        //睡眠3s钟保证线程t1创建对象完成
        Thread.sleep(3000);
        out.println("打印t1线程，list中第20个对象的对象头：");
        out.println((ClassLayout.parseInstance(listA.get(19)).toPrintable()));

        //创建线程t2竞争线程t1中已经退出同步块的锁
        Thread t2 = new Thread(() -> {
            //这里面只循环了30次！！！
            for (int i = 0; i < 30; i++) {
                A a =listA.get(i);
                synchronized (a){
                    //分别打印第19次和第20次偏向锁重偏向结果
                    if(i==18||i==19){
                        out.println("第"+ ( i + 1) + "次偏向结果");
                        out.println((ClassLayout.parseInstance(a).toPrintable()));
                    }
                }
            }
            synchronized (listA.get(0)){
                out.println("t2第二次锁定listA【0】的偏向结果");
                out.println((ClassLayout.parseInstance(listA.get(0)).toPrintable()));
            }
            synchronized (listA.get(0)){
                out.println("t2第二次锁定listA【19】的偏向结果");
                out.println((ClassLayout.parseInstance(listA.get(19)).toPrintable()));
            }
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        Thread.sleep(3000);
        out.println("打印list中第11个对象的对象头：");
        out.println((ClassLayout.parseInstance(listA.get(10)).toPrintable()));
        out.println("打印list中第19个对象的对象头：");
        out.println((ClassLayout.parseInstance(listA.get(18)).toPrintable()));
        out.println("打印list中第20个对象的对象头：");
        out.println((ClassLayout.parseInstance(listA.get(19)).toPrintable()));
        out.println("打印list中第26个对象的对象头：");
        out.println((ClassLayout.parseInstance(listA.get(25)).toPrintable()));
        out.println("打印list中第41个对象的对象头：");
        out.println((ClassLayout.parseInstance(listA.get(40)).toPrintable()));
    }

    public static void main2(String[] args) throws Exception {

        Thread.sleep(5000);
        List<A> listA = new ArrayList<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i <100 ; i++) {
                A a = new A();
                synchronized (a){
                    listA.add(a);
                }
            }
            try {
                Thread.sleep(28000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 30; i < 70; i++) {
                A a =listA.get(i);
                synchronized (a){
                    if(i == 49||i==50||i==62){
                        out.println("thread1 二次上锁 第"+ i + "个对象");
                        out.println((ClassLayout.parseInstance(a).toPrintable()));
                    }
                }
            }
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        Thread.sleep(3000);

        Thread t2 = new Thread(() -> {
            //这里循环了40次。达到了批量撤销的阈值
            for (int i = 0; i < 50; i++) {
                A a =listA.get(i);
                synchronized (a){
                }
            }
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();



//        //———————————分割线，前面代码不再赘述——————————————————————————————————————————
//        Thread.sleep(3000);
//        out.println("打印list中第11个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(10)).toPrintable()));
//        out.println("打印list中第26个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(25)).toPrintable()));
//        out.println("打印list中第46个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(45)).toPrintable()));
//        out.println("打印list中第90个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(89)).toPrintable()));
//
//
//        Thread t3 = new Thread(() -> {
//            for (int i = 40; i < 60; i++) {
//                A a =listA.get(i);
//                synchronized (a){
//                    if(i == 45||i==50||i==52){
//                        out.println("thread3 第"+ i + "次");
//                        out.println((ClassLayout.parseInstance(a).toPrintable()));
//                    }
//                }
//            }
//            try {
//                Thread.sleep(10000000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        t3.start();

//        Thread.sleep(10000);
//        Thread t4 = new Thread(() -> {
//            for (int i = 50; i < 70; i++) {
//                A a =listA.get(i);
//                synchronized (a){
//                    if(i == 59||i==60||i==62){
//                        out.println("thread4 第"+ i + "次");
//                        out.println((ClassLayout.parseInstance(a).toPrintable()));
//                    }
//                }
//            }
//        });
//        t4.start();

//        Thread.sleep(3000);
//        out.println("打印list中第11个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(10)).toPrintable()));
//        out.println("打印list中第26个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(25)).toPrintable()));
//        out.println("打印list中第46个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(45)).toPrintable()));
//        out.println("打印list中第90个对象的对象头：");
//        out.println((ClassLayout.parseInstance(listA.get(89)).toPrintable()));
//
//        out.println("重新输出新实例A");
//        out.println((ClassLayout.parseInstance(new A()).toPrintable()));
    }

    public static void main3(String[] args) throws Exception {
//        main2(args);

//        out.println("重新输出新实例A");
//        A a1 = new A();
//        out.println((ClassLayout.parseInstance(a1).toPrintable()));
//        System.out.println(Integer.toBinaryString(a1.hashCode()));
//        out.println((ClassLayout.parseInstance(a1).toPrintable()));
//        System.out.println(Integer.toBinaryString(a1.hashCode()));

        Unsafe unsafe = Unsafe.getUnsafe();
        AtomicReference atomicReference = new AtomicReference();
//        AbstractQueuedSynchronizer
    }
}

class A {
    boolean flag = false;
}
