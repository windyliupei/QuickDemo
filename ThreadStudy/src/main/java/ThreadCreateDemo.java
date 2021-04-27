import java.util.Date;

public class ThreadCreateDemo {

    public static void main(String[] args){

        //继承 Thread 类去创建线程。
        myThread();

        //实现 Runnable 去创建线程。
        runnable();

        //实现 Callable 去创建线程。
        //这个需要借助 Future 类


    }

    private static void runnable() {
        Thread thread = new Thread(new MyRunnable(),"MyRunnableThread");
        thread.start();
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
