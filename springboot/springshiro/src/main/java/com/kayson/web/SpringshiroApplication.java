package com.kayson.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/*
* 自定义的xxx.properties配置文件是不会被SpringBoot自动加载的，需要手动去进行加载，
* 这里的手动加载一般指的是注解的方式加载：
* 加载自定义属性文件的注解：@PropertySource("classpath:xxx.properties")，
* */
@SpringBootApplication
@PropertySource("classpath:application-redis.properties")
public class SpringshiroApplication {

    private static Logger logger = LogManager.getLogger(SpringshiroApplication.class);

    public static void main(String[] args) {
        logger.info("spring boot start");
        SpringApplication.run(SpringshiroApplication.class, args);
    }

    @EnableAsync
    @Configuration
    class TaskPoolConfig {

        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            //关闭线程池
            executor.setWaitForTasksToCompleteOnShutdown(true);
            executor.setAwaitTerminationSeconds(60);
            return executor;
        }
    }

/*
上面我们通过使用ThreadPoolTaskExecutor创建了一个线程池，同时设置了以下这些参数：
核心线程数10：线程池创建时候初始化的线程数
最大线程数20：线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
缓冲队列200：用来缓冲执行任务的队列
允许线程的空闲时间60秒：当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
线程池名的前缀：设置好了之后可以方便我们定位处理任务所在的线程池
线程池对拒绝任务的处理策略：这里采用了CallerRunsPolicy策略，当线程池没有处理能力的时候，该策略会直接在 execute
方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务

说明：setWaitForTasksToCompleteOnShutdown（true）该方法就是这里的关键，用来设置线程池关闭的时候等待所有任务都
完成再继续销毁其他的Bean，这样这些异步任务的销毁就会先于Redis线程池的销毁。同时，这里还设置了
setAwaitTerminationSeconds(60)，该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，
以确保应用最后能够被关闭，而不是阻塞住。
*/
}

/*
所以，如果 Web 应用程序采用了经典的三层分层结构的话，最好在持久层、业务层和控制层分别采用上述注解对分层中的类进行注释。
@Service用于标注业务层组件
@Controller用于标注控制层组件（如struts中的action）
@Repository用于标注数据访问组件，即DAO组件
@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
*/
