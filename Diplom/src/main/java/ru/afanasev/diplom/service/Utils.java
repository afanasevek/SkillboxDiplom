package ru.afanasev.diplom.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.mortennobel.imagescaling.ResampleOp;

public class Utils {
	public static String removeHtmlTags(String text) {
		String returnText = text.replaceAll("\\<[^>]*>", "");
		return returnText;
	}

	public static String generateUUID() {
		UUID secret = UUID.randomUUID();
		return secret.toString();
	}
	
	public static void rescaleImage(File image) throws IOException {
		ResampleOp resampleOp = new ResampleOp (100,200);
		BufferedImage getImage = ImageIO.read(image);
		BufferedImage scaleIm = resampleOp.filter(getImage, null);
		
	}
}
