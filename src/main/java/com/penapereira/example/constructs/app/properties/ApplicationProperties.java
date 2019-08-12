package com.penapereira.example.constructs.app.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app")
//codebeat:disable[TOO_MANY_IVARS]
public class ApplicationProperties {

	protected int windowMarginX;
	protected int windowMarginY;

	protected String linkColor;
	protected String linkColorHover;

	@Value("classpath:icon.png")
	protected Resource appIcon;
}
//codebeat:enable[TOO_MANY_IVARS]
