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

	/** Opens a link in the user's browser. Replaceable in tests, where there is no desktop. */
	@FunctionalInterface
	interface LinkOpener {
		void open(URI uri) throws IOException;
	}

	private static final Logger log = LoggerFactory.getLogger(HyperlinkMouseListener.class);

	private final ApplicationProperties props;
	private final LinkOpener linkOpener;
	private String lastText;

	public HyperlinkMouseListener(ApplicationProperties props) {
		this(props, uri -> Desktop.getDesktop().browse(uri));
	}

	HyperlinkMouseListener(ApplicationProperties props, LinkOpener linkOpener) {
		this.props = props;
		this.linkOpener = linkOpener;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		log.debug("Hyperlink text: " + lastText);
		try {
			linkOpener.open(new URI(lastText));
		} catch (IOException | URISyntaxException | RuntimeException ex) {
			log.error("Error opening link", ex);
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
	public void mousePressed(MouseEvent e) {
		// Nothing to do: the link opens on click.
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Nothing to do: the link opens on click.
	}
}
