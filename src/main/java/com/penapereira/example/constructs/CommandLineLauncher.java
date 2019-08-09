package com.penapereira.example.constructs;

import java.awt.EventQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.properties.ApplicationProperties;
import com.penapereira.example.constructs.properties.Messages;
import com.penapereira.example.constructs.ui.MainWindow;

@Component
public class CommandLineLauncher implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(CommandLineLauncher.class);

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
