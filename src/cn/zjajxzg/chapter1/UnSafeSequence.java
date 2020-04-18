package cn.zjajxzg.chapter1;

/**
 * 线程不安全的数值序列生成器
 *
 * @author zhigang.xu
 */
public class UnSafeSequence {

    private int value;

    /**
     * 返回一个唯一的数值
     */
    public int getNext() {
        return value++;
    }

    public static void main(String[] args) {

        UnSafeSequence unSafeSequence = new UnSafeSequence();
//        // 单线程情况下 10000次循环 value变成10000
//        for (int i = 0; i < 100; i++) {
//            unSafeSequence.getNext();
//        }
//        System.out.println("单线程：" + unSafeSequence.value);

        // 两个线程同时循环10000次 结果并不是想象的那样
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                unSafeSequence.getNext();
            }
            System.out.println("thread1: " + unSafeSequence.value);
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                unSafeSequence.getNext();
            }
            System.out.println("thread2: " + unSafeSequence.value);
        });

        thread1.start();
        thread2.start();

    }
}
