package com.plant.server.util.string;

import java.text.Collator;

import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {

	public static final int FIELD_DEFAULT_MAX_LENGTH = 256;
	
	private static Collator collator = null;
	private static Collator getCollator() {
		if (collator == null) {
			collator = Collator.getInstance();
			collator.setStrength(Collator.NO_DECOMPOSITION);
		}
		return collator;
	}
	
	public static boolean isEmpty(String s) {
		return org.springframework.util.StringUtils.isEmpty(s);
	}

	public static String randomString(int length) {
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    return RandomStringUtils.random(length, useLetters, useNumbers);
	}
	
	public static String replaceAllCharsExceptSpaces(String originalString, char toReplaceWith) {
		String s = new String();
		for (int i=0 ; i < originalString.length(); i++) {
			char c = originalString.charAt(i);
			if (c == ' ') {
				s+=" ";
			} else {
				s+=toReplaceWith;
			}
		}
//		return new String(originalString.replaceAll("([^\\s]+)", toReplaceWith));
		return s;
	}

	public static boolean equalsTrimedLowercase(boolean strict, String s1, String s2) {
		return compareTrimedLowercase(strict, s1, s2)==0;
	}
	@SuppressWarnings("null")
	private static int compareTrimedLowercase(boolean strict, String s1, String s2) {
		if (s1==null&&s2==null) {
			return 0;
		}
		if (s1==null&&s2!=null) {
			return -1;
		}
		if (s1!=null&&s2==null) {
			return 1;
		}
		if (strict) {
			return s1.compareTo(s2);
		} else {
			return getCollator().compare(getStringTrimedLowercase(s1), getStringTrimedLowercase(s2));
		}
	}
	private static String getStringTrimedLowercase(String s) {
		if (s==null) {
			return null;
		}
		return s.trim().toLowerCase();
	}

	public static int numberOfWords(String s) {
		if (s==null) {
			return 0;
		}
		return s.trim().split("\\s+").length;
	}

}
