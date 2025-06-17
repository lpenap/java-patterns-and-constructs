package com.penapereira.example.constructs.app;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.properties.ApplicationProperties;
import com.penapereira.example.constructs.app.properties.Messages;

@Component
public class ExamplesCommandLineRunner implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(ExamplesCommandLineRunner.class);

	@Autowired
	private ApplicationContext ctx;

	@Autowired
	Messages msg;

	@Autowired
	ApplicationProperties props;

	@Override
	public void run(String... args) throws Exception {
		List<String> beanNames = Arrays.asList(ctx.getBeanNamesForType(ExampleRunnerInterface.class));

		listExamples(beanNames);

		if (props.getEnableCommandLineRunner()) {
   			executeExamples(beanNames);
		}
	}

	private void listExamples(List<String> beanNames) {
		checkDebugEnabled();
		var i = beanNames.iterator();
		log.debug(String.format("%s (%s):", msg.getExamplesFound(), ExampleRunnerInterface.class.getSimpleName()));
		var count = 0;
		while (i.hasNext()) {
			var cleanName = i.next().replaceFirst("ExampleRunner", "");
			log.debug(String.format("%4d : %s", ++count, cleanName));
		}
	}

	private void executeExamples(List<String> beanNames) throws Exception {
		checkTraceEnabled();
		var i = beanNames.iterator();
		while (i.hasNext()) {
			log.trace(msg.getSeparator());
			((ExampleRunnerInterface) ctx.getBean(i.next())).runExample();
		}
	}

	private void checkTraceEnabled() {
		if (!log.isTraceEnabled()) {
			log.info(msg.getEnableTraceToSeeExamplesDetails());
		}
	}

	private void checkDebugEnabled() {
		if (!log.isDebugEnabled()) {
			log.info(msg.getEnableDebugToSeeExamplesList());
		}
	}
}
