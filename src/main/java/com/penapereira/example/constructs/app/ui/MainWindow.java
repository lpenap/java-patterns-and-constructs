package com.penapereira.example.constructs.app.ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.penapereira.example.constructs.app.properties.ApplicationProperties;
import com.penapereira.example.constructs.app.properties.Messages;

@Component
public class MainWindow extends JFrame {

	private static final Logger log = LoggerFactory.getLogger(MainWindow.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	Messages msg;

	@Autowired
	ApplicationProperties props;

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
		JPanel mainPanel = new JPanel(new GridLayout(4, 1));
		createCenteredTitle(msg.getGreeting(), mainPanel);
		mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		createCenteredLabelOnPanel(msg.getInfo(), mainPanel);
		createCenteredHyperlink(msg.getHomeUrl(), mainPanel);
		return mainPanel;
	}

	private void createCenteredTitle(String text, JPanel panel) {
		JLabel title = new JLabel(text, JLabel.CENTER);
		Font current = title.getFont();
		title.setFont(new Font(current.getName(), current.getStyle(), current.getSize() + 4));
		panel.add(title);
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

}
