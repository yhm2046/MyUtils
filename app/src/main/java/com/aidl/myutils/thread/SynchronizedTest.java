package com.aidl.myutils.thread;

/**
 * 下面是一个使用 synchronized 实现多线程同步的例子，该例子演示了使用两个线程分别对共享变量 count 进行自增操作，保证了多线程操作 count 的线程安全性。
 */
public class SynchronizedTest {
    private static int count = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    increment();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    increment();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            /**
             * t1.join() 是一个 Java 中的线程方法，调用该方法会让当前线程等待 t1 线程执行结束。具体来说，它会阻塞当前线程，直到 t1 线程执行完毕后才会继续执行当前线程。
             *
             * 在使用 join() 方法时，如果 t1 线程已经执行完毕，则 join() 方法会立即返回。如果 t1 线程尚未执行完毕，则当前线程会一直阻塞，直到 t1 线程执行完毕为止。
             *
             * 例如，假设有两个线程 t1 和 t2，t1 线程执行时间较长，t2 线程需要等待 t1 线程执行完毕后再执行。
             * 可以在 t1 线程中调用 t2.join() 方法，让 t1 线程等待 t2 线程执行完毕后再执行。同理，在 t2 线程中调用 t1.join() 方法也可以实现相同的效果。
             */
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count: " + count);
    }

    private synchronized static void increment() {
        count++;
    }
}

