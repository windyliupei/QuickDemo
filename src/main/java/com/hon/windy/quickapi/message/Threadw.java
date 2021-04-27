package com.hon.windy.quickapi.message;

import java.util.concurrent.Callable;

public class Threadw implements Callable {

    LockObject mLockObject = null;
    Boolean mCanWork = null;
    String mName = null;

    public Threadw(LockObject lockObject,Boolean canWork ,String name){
        mLockObject = lockObject;
        mCanWork = canWork;
        mName = name;
    }


    public void run() {

//        for (int i = 0;i<=10;i++){
//
//            synchronized (mLockObject){
//                if (mCanWork){
//                    try {
//                        System.out.println("");
//                        System.out.format("I am %s", mName);
//                        mCanWork = false;
//                        mLockObject.wait(1000);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }else{
//                    mLockObject.notify();
//                    mCanWork = true;
//                }
//            }
//        }

        try {
            Thread.sleep(10_000);
            System.out.println("Thread finished!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object call() throws Exception {
        run();
        return null;
    }
}
