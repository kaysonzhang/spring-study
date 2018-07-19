package com.kayson.web.asyncTask;

import com.kayson.web.controller.IndexController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author by kayson
 * @data 2018/7/18 16:10
 * @description @Async实现异步调用：自定义线程池
 */

@Component
public class AsyncThreadPoolTask {

    private static Logger log = LogManager.getLogger(IndexController.class);

    public static Random random = new Random();

    /*
    * 在定义了线程池之后，我们如何让异步调用的执行任务使用这个线程池中的资源来运行呢？
    * 方法非常简单，我们只需要在@Async注解中指定线程池名如taskExecutor即可
    * */
    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
    }
}
