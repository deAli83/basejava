package ru.javawebinar.basejava;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private int counter;
    private static final Object LOCK = new Object();

    private static int thread1 = 1;
    private static int thread2 = 2;

    public static void main(String[] args) throws InterruptedException {
        deadLock(thread1, thread2);
        deadLock(thread2, thread1);

//        System.out.println(Thread.currentThread().getName());
//
//        Thread thread0 = new Thread() {
//            @Override
//            public void run() {
//                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
//            }
//        };
//        thread0.start();

//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
//            }
//
//            private void inc() {
//                synchronized (this) {
//                    counter++;
//                }
//            }
//
//        }).start();
//
//        System.out.println(thread0.getState());
//
//        final MainConcurrency mainConcurrency = new MainConcurrency();
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
//
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.inc();
//                }
//            });
//            thread.start();
//            threads.add(thread);
//        }
//
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println(mainConcurrency.counter);
//    }
//
//    private synchronized void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//        counter++;
//                wait();
//                readFile
//                ...
//        }
    }

    private static void deadLock(Object thread1, Object thread2) {
        new Thread(() -> {
            synchronized (thread1) {
                System.out.println("First " + thread1);
                System.out.println("Second " + thread2);
                synchronized (thread2) {
                    System.out.println("Third " + thread2);
                }
            }
        }).start();
    }

}