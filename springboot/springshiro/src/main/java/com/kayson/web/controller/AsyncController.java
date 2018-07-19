package com.kayson.web.controller;

import com.kayson.web.asyncTask.AsyncTask;
import com.kayson.web.asyncTask.AsyncThreadPoolTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * @author by kayson
 * @data 2018/7/18 15:19
 * @description
 */
@RestController
@EnableAsync //这个最好放在入口程序
public class AsyncController {
    private static Logger logger = LogManager.getLogger(IndexController.class);

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private AsyncThreadPoolTask asyncThreadPoolTask;

    //测试异步任务没有线程池的
    @RequestMapping(value = "/async")
    public String test(){

        long currentTimeMillis = System.currentTimeMillis();

        try {
            Future<String> ta = asyncTask.doAsync();
            Future<String> tb = asyncTask.doAsyncB();
            //用isCancelled判断异步任务是否取消，isDone判断任务是否执行结束
            //用while来等待异步程序处理完成
            /*while(true) {
                if(ta.isDone() && tb.isDone()) {
                    // 三个任务都调用完成，退出循环等待
                    break;
                }
                Thread.sleep(1000);
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long currentTimeMillis1 = System.currentTimeMillis();
        return "task任务总耗时:"+(currentTimeMillis1-currentTimeMillis)+"ms";
    }

    //测试异步任务线程池
    @RequestMapping(value = "/asynctp")
    public void asynctp() throws Exception {

        asyncThreadPoolTask.doTaskOne();
        asyncThreadPoolTask.doTaskTwo();
        asyncThreadPoolTask.doTaskThree();

        //Thread.currentThread().join();
    }


}
