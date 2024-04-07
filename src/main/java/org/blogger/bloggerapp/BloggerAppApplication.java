package org.blogger.bloggerapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class BloggerAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(BloggerAppApplication.class, args);
    }

}
