import java.util.Date;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class ThreadCreateDemo {

    public static void main(String[] args) throws InterruptedException {

        //继承 Thread 类去创建线程。
        //myThread();

        //实现 Runnable 去创建线程。
        //runnable();

        //实现 Callable 去创建线程。
        //这个需要借助 Future 类
        //myCallable();

        //实现 Callable 去创建线程。
        //https://www.liaoxuefeng.com/wiki/1252599548343744/1306581182447650
        //这个需要借助 ComplementFuture 类
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(
                new Supplier<String>() {
                    @Override
                    public String get() {
                        try {
                            System.out.println("Enter CompletableFuture method!");
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return "Internal finished";
                    }
                }
        );
        stringCompletableFuture.whenCompleteAsync((str,err)->{
            System.out.println(str);
        });


        System.out.println("Main Thread was finished!");

        Thread.sleep(10000);

        //线程池
        //threadPool();


    }

    private static void threadPool() {
        MyRunnable myRunnable = new MyRunnable();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(myRunnable);
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"执行时间:"+new Date().getTime());
        }
    }

    private static void myCallable() {
        MyCallable myCallable = new MyCallable();
        FutureTask<String> stringFutureTask = new FutureTask<String>(myCallable);
        Thread t1 = new Thread(stringFutureTask,"Mycallable");
        t1.start();
        try {
            String callableResult = stringFutureTask.get(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Main 主线程"+new Date().getTime());
        }
    }

    private static void runnable() {
        Thread thread = new Thread(new MyRunnable(),"MyRunnableThread");
        thread.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("Main 主线程"+new Date().getTime());
        }
    }

    private static void myThread() {
        MyThread myThread = new MyThread();
        myThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Main 主线程"+new Date().getTime());
        }
    }


}
