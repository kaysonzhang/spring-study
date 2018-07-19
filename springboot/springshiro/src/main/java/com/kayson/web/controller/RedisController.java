package com.kayson.web.controller;

import com.kayson.web.Utils.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by kayson
 * @data 2018/7/18 11:30
 * @description
 */
@RestController
@RequestMapping("/")
public class RedisController {
    private static Logger logger = LogManager.getLogger(RedisController.class);

    @Autowired //这里必须要注解的哦
    private RedisUtil redisUtil;

    @RequestMapping("redis")
    public String test(){
        redisUtil.set("a", "v");
        logger.info("redis"+redisUtil.get("a"));
        return "REDIS";
    }
}
