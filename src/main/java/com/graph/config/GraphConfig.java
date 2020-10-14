package com.graph.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {com.graph.impl.InOrderTreeTravelNonRecursive.class})
public class GraphConfig {
}
