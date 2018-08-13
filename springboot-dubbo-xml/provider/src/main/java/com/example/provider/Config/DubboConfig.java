package com.example.provider.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ImportResource({ "classpath:*.xml" })
public class DubboConfig {
}
