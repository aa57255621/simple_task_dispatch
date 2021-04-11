package com.example.demo.config;

/**
 * @author : lp225484
 * @date : 2021/04/11
 * @description:
 */
public @interface ExcludeScan {
    public boolean excludeScan() default true;
}
