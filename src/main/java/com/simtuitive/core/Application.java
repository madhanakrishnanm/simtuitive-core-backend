package com.simtuitive.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		 new SpringApplicationBuilder(Application.class)          
        .run(args);
	}
}
