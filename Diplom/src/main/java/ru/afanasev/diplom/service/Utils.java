package ru.afanasev.diplom.service;

public class Utils {
	public static String removeHtmlTags (String text) {
		String returnText = text.replaceAll("\\<[^>]*>", "");
		return returnText;
	}
}
