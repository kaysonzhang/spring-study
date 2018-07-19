package com.kayson.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ShiroStatessApplication {

    private static Logger logger = LogManager.getLogger(ShiroStatessApplication.class);

    public static void main(String[] args) {
        logger.info("spring boot start");
        SpringApplication.run(ShiroStatessApplication.class, args);
    }
}

/*
所以，如果 Web 应用程序采用了经典的三层分层结构的话，最好在持久层、业务层和控制层分别采用上述注解对分层中的类进行注释。

@Service用于标注业务层组件

@Controller用于标注控制层组件（如struts中的action）

@Repository用于标注数据访问组件，即DAO组件

@Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
*/
