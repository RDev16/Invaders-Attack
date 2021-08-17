package com.isi.tools;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage load(String name) {
		try {
			return ImageIO.read(ImageLoader.class.getResource("/com/isi/assets/" + name));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}		
		return null;
	}
}