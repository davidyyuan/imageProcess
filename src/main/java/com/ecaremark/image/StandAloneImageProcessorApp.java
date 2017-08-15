package com.ecaremark.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

import com.ecaremark.entity.Address;
import com.ecaremark.entity.MemberInfo;

@SpringBootApplication
@EnableIntegration
public class StandAloneImageProcessorApp {
	final static String IMAGE_TYPE = "jpg";
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(StandAloneImageProcessorApp.class, args);
		//execute();
	}

	private static void execute() throws FileNotFoundException, IOException {
		InputStream ip = new FileInputStream(new File("rsz_img_7358.jpg"));
		MemberInfo mi = createSampleMember();
		IImageProcessor imageProcessor = new ImageProcessor();
		BufferedImage mergedImage = imageProcessor.createImageWithHeader(ip, mi);
		ImageIO.write(mergedImage, IMAGE_TYPE, new File("test.jpg"));
	}
	
	static MemberInfo createSampleMember() {
		MemberInfo mi = new MemberInfo(891234567, "David", "Yuan", "01/01/2014", 
				new Address("901 E Colins St.", "Richardson", "75081", "Texas"),
				"214-555-6789","john.doe@fictious.com");
		return mi;
	}
}



