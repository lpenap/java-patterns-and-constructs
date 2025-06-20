package com.penapereira.example.constructs.app.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.properties.ApplicationProperties;
import com.penapereira.example.constructs.app.properties.Messages;
import com.penapereira.example.constructs.app.ExampleRunnerInterface;

@Component
public class MainWindow extends JFrame {

	private static final Logger log = LoggerFactory.getLogger(MainWindow.class);

        private static final long serialVersionUID = 1L;

    private JTextArea outputArea;
    private JCheckBox preserveLogCheck;

	@Autowired
	Messages msg;

        @Autowired
        ApplicationProperties props;

       @Autowired
       ApplicationContext ctx;

       private java.util.Map<String, ExampleRunnerInterface> examples;

	public MainWindow() {
		super();
	}

	public void initializeFrame() {
		prepareWindow();
		setContentPane(getMainComponent());
		adjustWindow();
	}

	private void adjustWindow() {
		pack();
		setLocationRelativeTo(null);
		setSize(getWidth() + 50, getHeight() + 50);
	}

	private void prepareWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(msg.getWindowTitle());
		loadIcon();
	}

	private void loadIcon() {
		try {
			var icon = ImageIO.read(props.getAppIcon().getInputStream());
			setIconImage(icon);
		} catch (IOException e) {
			log.warn("Could not load app icon", e);
		}
	}

        private JPanel getMainComponent() {
                JPanel mainPanel = new JPanel(new BorderLayout());

               JPanel infoPanel = new JPanel(new GridLayout(4, 1));
               createCenteredTitle(msg.getGreeting(), infoPanel);
               createCenteredLabelOnPanel(msg.getInfo(), infoPanel);
               createCenteredHyperlink(msg.getHomeUrl(), infoPanel);
               mainPanel.add(infoPanel, BorderLayout.NORTH);

               mainPanel.add(createExamplesPanel(), BorderLayout.WEST);

               outputArea = new JTextArea(10, 40);
               outputArea.setEditable(false);
               JScrollPane scrollPane = new JScrollPane(outputArea);
               scrollPane.setBorder(javax.swing.BorderFactory.createTitledBorder(msg.getOutputTitle()));

               preserveLogCheck = new JCheckBox(msg.getPreserveLog());

               JPanel outputPanel = new JPanel(new BorderLayout());
               outputPanel.add(preserveLogCheck, BorderLayout.NORTH);
               outputPanel.add(scrollPane, BorderLayout.CENTER);

               mainPanel.add(outputPanel, BorderLayout.CENTER);

               return mainPanel;
        }

	private void createCenteredTitle(String text, JPanel panel) {
		JLabel title = new JLabel(text, JLabel.CENTER);
		Font current = title.getFont();
		title.setFont(new Font(current.getName(), current.getStyle(), current.getSize() + 4));
		panel.add(title);
		panel.add(new JSeparator(SwingConstants.HORIZONTAL));
	}

	private void createCenteredHyperlink(String text, JPanel panel) {
		JLabel hyperlink = new JLabel(text, JLabel.CENTER);
		hyperlink.setForeground(Color.decode(props.getLinkColor()));
		hyperlink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hyperlink.addMouseListener(new HyperlinkMouseListener(props));
		panel.add(hyperlink);
	}

        private void createCenteredLabelOnPanel(String text, JPanel panel) {
                JLabel label = new JLabel(text);
                label.setHorizontalAlignment(JLabel.CENTER);
                panel.add(label);
        }

        public void appendOutput(String text) {
                if (outputArea != null) {
                        outputArea.append(text);
                        outputArea.setCaretPosition(outputArea.getDocument().getLength());
                }
        }

       private JPanel createExamplesPanel() {
               examples = ctx.getBeansOfType(ExampleRunnerInterface.class);

               JPanel panel = new JPanel();
               panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
               panel.setBorder(javax.swing.BorderFactory.createTitledBorder(msg.getExamplesFound()));

               javax.swing.JButton allButton = new javax.swing.JButton("Run All");
               allButton.addActionListener(e -> runAllExamples());
               panel.add(allButton);

               examples.forEach((name, runner) -> {
                       String clean = name.replaceFirst("ExampleRunner", "");
                       javax.swing.JButton btn = new javax.swing.JButton(clean);
                       btn.addActionListener(e -> runExample(runner));
                       panel.add(btn);
               });

               return panel;
       }

       private void runExample(ExampleRunnerInterface runner) {
               if (!preserveLogCheck.isSelected()) {
                       outputArea.setText("");
               }
               new Thread(() -> {
                       try {
                               log.trace(msg.getSeparator());
                               runner.runExample();
                       } catch (Exception e) {
                               log.error("Error executing example", e);
                       }
               }).start();
       }

       private void runAllExamples() {
               if (!preserveLogCheck.isSelected()) {
                       outputArea.setText("");
               }
               new Thread(() -> {
                       examples.values().forEach(r -> {
                               try {
                                       log.trace(msg.getSeparator());
                                       r.runExample();
                               } catch (Exception e) {
                                       log.error("Error executing example", e);
                               }
                       });
               }).start();
       }

}
