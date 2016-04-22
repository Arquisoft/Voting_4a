package es.uniovi.asw.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * PasswordGenerator
 * @author ivan
 */
public class PasswordGenerator {

	public static String generate(int lenght) {
		String password = "";
		
		for (int i = 0; i < lenght; i++) {
		    password += RandomStringUtils.randomAlphanumeric(1);
		}
		
		return password;
	}
	
}
