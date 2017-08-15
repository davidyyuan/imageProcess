package com.ecaremark.image;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JEditorPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecaremark.entity.MemberInfo;

@Component
public class ImageProcessor implements IImageProcessor {
	static final int HEADER_OFFSET = 10;
	static final int HEADER_HEIGHT = 200;
	
	@Autowired
	private IHeaderContentBuilder builder = new HeaderContentHtmlBuilder();

	@Override
	public BufferedImage createImageWithHeader(InputStream ip, MemberInfo mi) throws IOException {
		final BufferedImage image = ImageIO.read(ip);
		BufferedImage header = buildHeaderImage(mi, image.getWidth(), image.getType());
		return joinBufferedImage(header, image, image.getType());
	}

	private BufferedImage joinBufferedImage(BufferedImage header, BufferedImage body, int type) {
		BufferedImage mergedImage = new BufferedImage(body.getWidth(), body.getHeight() + header.getHeight() + HEADER_OFFSET, type);
		mergedImage.createGraphics().drawImage(header, 0, 0, null);
		mergedImage.createGraphics().drawImage(body, 0, header.getHeight(), null);
		mergedImage.createGraphics().dispose();
		return mergedImage;
	}

	private BufferedImage buildHeaderImage(MemberInfo mi, int width, int imageType) {
		BufferedImage img = new BufferedImage(width, HEADER_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = img.createGraphics();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, width, HEADER_HEIGHT);
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		String html = builder.buildContent(mi);
		JEditorPane jep = new JEditorPane("text/html", html);
		jep.setSize(width, HEADER_HEIGHT);
		jep.print(g2d);
		return img;
	}
}

