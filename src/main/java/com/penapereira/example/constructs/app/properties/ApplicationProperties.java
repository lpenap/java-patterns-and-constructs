package com.penapereira.example.constructs.app.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

	protected int windowMarginX;
	protected int windowMarginY;
	
	protected String linkColor;
	protected String linkColorHover;
}
