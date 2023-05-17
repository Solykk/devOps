package com.dev.ops.admin.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Dmitriy Lyashenko
 */
@Configuration
@ComponentScan(basePackages = {"com.dev.ops.admin", "com.dev.ops.commons"})
@EnableJpaRepositories(basePackages = {"com.dev.ops.admin", "com.dev.ops.commons"})
@EntityScan(basePackages = {"com.dev.ops.admin", "com.dev.ops.commons"})
public class AdminConfiguration {
}
