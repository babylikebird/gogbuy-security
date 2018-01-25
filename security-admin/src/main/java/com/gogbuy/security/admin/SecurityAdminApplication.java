package com.gogbuy.security.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class SecurityAdminApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SecurityAdminApplication.class, args);
	}
}
