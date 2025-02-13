package ca.sheridancollege.giangma;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestEncryption {

	public static String encrypt(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
//	public static void main(String[] args) {
//		String password = "123";
//		String encoded = encrypt(password);
//		System.out.println(encoded);
//		encoded = encrypt(password);
//		System.out.println(encoded);
//	}
}
