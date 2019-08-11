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
			var icon = ImageIO.read(props.getAppIcon().getFile());
			setIconImage(icon);
		} catch (IOException e) {
			log.warn("Could not load app icon", e);
		}
	}

	private JPanel getMainComponent() {
		JPanel mainPanel = new JPanel(new GridLayout(4, 1));
		var title = createCenteredLabelOnPanel(msg.getGreeting(), mainPanel);
		formatLabelAsTitle(title);
		mainPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
		createCenteredLabelOnPanel(msg.getInfo(), mainPanel);
		var label = createCenteredLabelOnPanel(msg.getHomeUrl(), mainPanel);
		formatLabelAsHyerlink(label);
		return mainPanel;
	}

	private void formatLabelAsTitle(JLabel title) {
		Font current = title.getFont();
		title.setFont(new Font(current.getName(), current.getStyle(), current.getSize() + 4));
	}

	private void formatLabelAsHyerlink(JLabel label) {
		label.setForeground(Color.decode(props.getLinkColor()));
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new HyperlinkMouseListener(props));
	}

	private JLabel createCenteredLabelOnPanel(String text, JPanel panel) {
		JLabel label = new JLabel(text);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		panel.add(label);
		return label;
	}

}
