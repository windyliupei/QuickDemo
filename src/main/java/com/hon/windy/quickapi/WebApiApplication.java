package com.hon.windy.quickapi;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


@SpringBootApplication
public class WebApiApplication {

    public static void main(String[] args){
        SpringApplication.run(WebApiApplication.class, args);

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


    }

}
