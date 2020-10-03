package ru.afanasev.diplom.service;

import java.util.UUID;

public class Utils {
	public static String removeHtmlTags(String text) {
		String returnText = text.replaceAll("\\<[^>]*>", "");
		return returnText;
	}

	public static String generateUUID() {
		UUID secret = UUID.randomUUID();
		return secret.toString();
	}
}
