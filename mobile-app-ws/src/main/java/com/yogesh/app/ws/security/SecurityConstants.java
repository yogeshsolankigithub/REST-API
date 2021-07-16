package com.yogesh.app.ws.security;

import com.yogesh.app.ws.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME=864000000;//10 days
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	public static final String SIGN_UP_URL="/users";
	
	public static String getTokenSecrte()
	{
		AppProperties appProperties=(AppProperties) SpringApplicationContext.getBeans("AppProperties");
		return appProperties.getTokenSecret();
	}
}
