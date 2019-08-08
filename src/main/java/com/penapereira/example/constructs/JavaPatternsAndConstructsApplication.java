package com.penapereira.example.constructs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JavaPatternsAndConstructsApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(JavaPatternsAndConstructsApplication.class).headless(false).run(args);
	}

}
