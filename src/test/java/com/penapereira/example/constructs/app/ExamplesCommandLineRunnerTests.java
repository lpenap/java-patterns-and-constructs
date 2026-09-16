package com.penapereira.example.constructs.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericApplicationContext;

import com.penapereira.example.constructs.app.properties.ApplicationProperties;
import com.penapereira.example.constructs.app.properties.Messages;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

class ExamplesCommandLineRunnerTests {

    private final Logger runnerLog = (Logger) LoggerFactory.getLogger(ExamplesCommandLineRunner.class);
    private final Level originalLevel = runnerLog.getLevel();
    private final ListAppender<ILoggingEvent> logged = new ListAppender<>();

    private final AtomicInteger executions = new AtomicInteger();
    private GenericApplicationContext ctx;
    private ApplicationProperties props;
    private Messages msg;

    @BeforeEach
    void setUp() {
        logged.start();
        runnerLog.addAppender(logged);

        ctx = new GenericApplicationContext();
        ctx.registerBean("fakeExampleRunner", ExampleRunnerInterface.class, () -> executions::incrementAndGet);
        ctx.refresh();

        props = new ApplicationProperties();
        msg = new Messages();
        msg.setExamplesFound("Examples");
        msg.setSeparator("---");
        msg.setEnableTraceToSeeExamplesDetails("enable trace");
        msg.setEnableDebugToSeeExamplesList("enable debug");
    }

    @AfterEach
    void tearDown() {
        runnerLog.detachAppender(logged);
        runnerLog.setLevel(originalLevel);
        ctx.close();
    }

    private ExamplesCommandLineRunner runner() {
        return new ExamplesCommandLineRunner(ctx, msg, props);
    }

    private boolean logged(String text) {
        return logged.list.stream().anyMatch(e -> e.getFormattedMessage().contains(text));
    }

    @Test
    void doesNothingWhenDisabled() throws Exception {
        props.setEnableCommandLineRunner(false);

        runner().run();

        assertEquals(0, executions.get());
        assertTrue(logged.list.isEmpty());
    }

    @Test
    void listsAndExecutesExamplesWhenTraceIsEnabled() throws Exception {
        props.setEnableCommandLineRunner(true);
        runnerLog.setLevel(Level.TRACE);

        runner().run();

        assertEquals(1, executions.get());
        assertTrue(logged("Examples (ExampleRunnerInterface):"));
        assertTrue(logged("   1 : fake"));
        assertTrue(logged("---"));
        assertFalse(logged("enable trace"));
        assertFalse(logged("enable debug"));
    }

    @Test
    void tellsHowToSeeDetailsWhenLogLevelIsTooHigh() throws Exception {
        props.setEnableCommandLineRunner(true);
        runnerLog.setLevel(Level.INFO);

        runner().run();

        assertEquals(1, executions.get());
        assertTrue(logged("enable debug"));
        assertTrue(logged("enable trace"));
        assertFalse(logged("   1 : fake"));
    }
}
