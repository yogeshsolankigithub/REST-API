package com.yogesh.app.ws.shared;

import java.awt.AlphaComposite;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class Utils {
	
	private final  Random random=new SecureRandom();
	private final String ALPHABET="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final int ITERATIONS=10000;
	private final int KEY_LENGTH=256;
	
	
	public String generateUserId(int length)
	{
		return generateRandomString(length);
	}

	private String generateRandomString(int length) {
		StringBuilder sb=new StringBuilder(length);
		for(int i=0;i<length;i++)
		{
			sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
		}
		
		return new String(sb);
	}

}
