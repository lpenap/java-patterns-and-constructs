package com.penapereira.example.constructs.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;

	public Main() {
		super();
		initializeFrame();
	}

	protected void initializeFrame() {
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(getMainComponent());
	}

	private JPanel getMainComponent() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		var label = new JLabel("test");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		mainPanel.add(label, BorderLayout.CENTER);
		return mainPanel;
	}
}
