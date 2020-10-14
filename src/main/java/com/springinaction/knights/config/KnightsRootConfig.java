package com.springinaction.knights.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(KnightConfig.class)
@ImportResource("classpath:knights.xml")
/**
 * javaConfig中引入其他配置的两种方式：
 * 1. @Import引入JavaConfig配置文件
 * 2. @ImportResource引入xml配置文件
 */
public class KnightsRootConfig {
}
