package com.bzq.matrixmall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MatrixMallAdminSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(MatrixMallAdminSpringBootApplication.class, args);
    }
}
