package com.kayson.api.exception;

/**
 * @author by kayson
 * @data 2018/7/17 9:33
 * @description
 */
public class UnauthorizedException extends RuntimeException {

        public UnauthorizedException(String msg) {
            super(msg);

        }



        public UnauthorizedException() {
            super();

        }

}
