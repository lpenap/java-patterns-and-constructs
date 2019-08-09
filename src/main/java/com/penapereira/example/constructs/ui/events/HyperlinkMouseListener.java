package com.penapereira.example.constructs.ui.events;

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

import com.penapereira.example.constructs.properties.ApplicationProperties;

public class HyperlinkMouseListener implements MouseListener {

	private Logger log = LoggerFactory.getLogger(HyperlinkMouseListener.class);

	ApplicationProperties props;

	public HyperlinkMouseListener(ApplicationProperties props) {
		this.props = props;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String uriText = ((JLabel) e.getComponent()).getText();
		log.debug("Hyperlink text: " + uriText);
		try {
			Desktop.getDesktop().browse(new URI(uriText));
		} catch (IOException | URISyntaxException e1) {
			log.error("Error opening link", e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel label = (JLabel) e.getComponent();
		label.setForeground(Color.decode(props.getLinkColorHover()));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel label = (JLabel) e.getComponent();
		label.setForeground(Color.decode(props.getLinkColor()));
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
