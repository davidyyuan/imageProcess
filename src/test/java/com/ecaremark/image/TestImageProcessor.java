package com.ecaremark.image;

import static org.testng.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.util.ResourceUtils;
import org.testng.annotations.Test;

import com.ecaremark.entity.Address;
import com.ecaremark.entity.MemberInfo;

public class TestImageProcessor {

	private ImageProcessor target = new ImageProcessor();
	
	@Test
	public void test_createImageWithHeader() throws IOException {
		File file = ResourceUtils.getFile(this.getClass().getResource("/rsz_img_7358.jpg"));
		InputStream ip = new FileInputStream(file);
		MemberInfo mi = createSampleMember();
		BufferedImage finalImg = target.createImageWithHeader(ip, mi);
		System.out.println("-----------------tartar");
		InputStream ip2 = new FileInputStream(file); 
		final BufferedImage image = ImageIO.read(ip2);
		assertEquals(finalImg.getHeight(), image.getHeight()+ImageProcessor.HEADER_HEIGHT + ImageProcessor.HEADER_OFFSET);
		assertEquals(finalImg.getWidth(), image.getWidth());
		assertEquals(finalImg.getType(), image.getType());
	}
	
	private MemberInfo createSampleMember() {
		return new MemberInfo(891234567, "David", "Yuan", "01/01/2014", 
				new Address("901 E Colins St.", "Richardson", "75081", "Texas"),
				"214-555-6789","john.doe@fictious.com");
	}
}
