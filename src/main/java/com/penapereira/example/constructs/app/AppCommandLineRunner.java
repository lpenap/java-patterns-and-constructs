package com.penapereira.example.constructs.app;

import java.awt.EventQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.properties.Messages;
import com.penapereira.example.constructs.app.ui.MainWindow;

@Component
public class AppCommandLineRunner implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(AppCommandLineRunner.class);

	@Autowired
	MainWindow main;

	@Autowired
	Messages msg;

	@Override
	public void run(String... args) throws Exception {
		log.info(msg.getGreeting());
		log.info(msg.getHomeUrl());
		EventQueue.invokeLater(() -> {
			main.initializeFrame();
			main.setVisible(true);
		});
	}

}
