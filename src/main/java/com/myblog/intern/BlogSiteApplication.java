package com.myblog.intern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BlogSiteApplication {
	public static void main(String[] args) {

		SpringApplication.run(BlogSiteApplication.class, args);
	}
}
