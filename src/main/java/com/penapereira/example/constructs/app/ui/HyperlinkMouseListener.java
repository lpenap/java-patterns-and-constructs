package com.penapereira.example.constructs.app.ui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.penapereira.example.constructs.app.properties.ApplicationProperties;

public class HyperlinkMouseListener implements MouseListener {

	private Logger log = LoggerFactory.getLogger(HyperlinkMouseListener.class);

	ApplicationProperties props;

	private String lastText;

	public HyperlinkMouseListener(ApplicationProperties props) {
		this.props = props;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		log.debug("Hyperlink text: " + lastText);
		try {
			Desktop.getDesktop().browse(new URI(lastText));
		} catch (IOException | URISyntaxException e1) {
			log.error("Error opening link", e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel) e.getComponent();
		this.lastText = label.getText();
		label.setText(String.format("<html><a href=''>%s</a></html>", lastText));
		label.setForeground(Color.decode(props.getLinkColorHover()));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getComponent();
		label.setText(lastText);
		label.setForeground(Color.decode(props.getLinkColor()));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
