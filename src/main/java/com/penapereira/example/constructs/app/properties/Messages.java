package com.penapereira.example.constructs.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "msg")
public class Messages {

	protected String greeting;
	protected String windowTitle;
	protected String homeUrl;
	protected String info;
}
