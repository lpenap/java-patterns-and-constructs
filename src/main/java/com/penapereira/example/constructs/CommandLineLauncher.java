package com.penapereira.example.constructs;

import java.awt.EventQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.ui.Main;

@Component
public class CommandLineLauncher implements CommandLineRunner {
	private static Logger log = LoggerFactory.getLogger(CommandLineLauncher.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting...");
		EventQueue.invokeLater(() -> {
			var main = new Main();
			main.setVisible(true);
		});
	}

}
