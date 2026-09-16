package com.penapereira.example.constructs.app;

import java.awt.EventQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.properties.Messages;
import com.penapereira.example.constructs.app.ui.MainWindow;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AppCommandLineRunner implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(AppCommandLineRunner.class);

	private final MainWindow main;
	private final Messages msg;

	@Override
	public void run(String... args) throws Exception {
		EventQueue.invokeLater(() -> {
			main.initializeFrame();
			main.setVisible(true);
		});
		log.info(msg.getSeparator());
		log.info(msg.getGreeting());
		log.info(msg.getHomeUrl());
		log.info(msg.getInstructions());
	}

}
