package com.ecaremark.image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import com.ecaremark.entity.MemberInfo;

public interface IImageProcessor {
	BufferedImage createImageWithHeader(InputStream ip, MemberInfo mi) throws IOException;
}