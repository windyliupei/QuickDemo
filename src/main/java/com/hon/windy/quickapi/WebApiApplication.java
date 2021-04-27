package com.hon.windy.quickapi;


import com.hon.windy.quickapi.message.LockObject;
import com.hon.windy.quickapi.message.Threadw;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

@SpringBootApplication
public class WebApiApplication {

    public static void main(String[] args){
        //SpringApplication.run(WebApiApplication.class, args);

         //System.out.println((3 * 0.1f) == 0.3f);
         //System.out.println((3 * 0.1) == 0.3);

         /*try{
             int i = 5 / 0;
             System.out.println("Finished!");
         }catch (Exception e){
             System.out.println("Exception!");
         }finally {
             System.out.println("Finally!");
         }*/

        LockObject mLockObject = new LockObject();
        Threadw tw1 = new Threadw(mLockObject,true,"T1");
        //Threadw tw2 = new Threadw(mLockObject,false,"T2");

        FutureTask<Object> futureTask1 = new FutureTask<>(tw1);
        //FutureTask<Object> futureTask2 = new FutureTask<>(tw2);

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Thread t1 = new Thread(futureTask1);
        //Thread t2 = new Thread(futureTask2);


        //t2.start();
        t1.start();

        {
            while (futureTask1.isDone()) {
                System.out.println("Future is done!");
            }
            System.out.println("Main Thread Finished!");
        }
    }

}
