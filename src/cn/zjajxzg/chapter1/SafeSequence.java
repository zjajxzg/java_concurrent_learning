package cn.zjajxzg.chapter1;

/**
 * 线程安全的数值序列生成器
 *
 * @author zhigang.xu
 */
public class SafeSequence {
    private int value;

    /**
     * 返回一个唯一的数值
     */
    public synchronized int getNext() {
        return value++;
    }

    public static void main(String[] args) {
        SafeSequence safeSequence = new SafeSequence();
//        // 单线程情况下 10000次循环 value变成10000
//        for (int i = 0; i < 100; i++) {
//            safeSequence.getNext();
//        }
//        System.out.println("单线程：" + safeSequence.value);

        // 两个线程同时循环10000次 最大值肯定是20000
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                safeSequence.getNext();
            }
            System.out.println("thread1: " + safeSequence.value);
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                safeSequence.getNext();
            }
            System.out.println("thread2: " + safeSequence.value);
        });

        thread1.start();
        thread2.start();
    }
}
