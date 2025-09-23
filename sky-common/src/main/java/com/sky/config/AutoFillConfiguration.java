package com.sky.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 自动填充配置类
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.sky.aspect")
public class AutoFillConfiguration {
}