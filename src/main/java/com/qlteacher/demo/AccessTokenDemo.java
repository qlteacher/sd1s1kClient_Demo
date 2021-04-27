package com.qlteacher.demo;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class AccessTokenDemo {
	
	public static String getAccessToken() throws OAuthSystemException, OAuthProblemException {
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthClientRequest request = OAuthClientRequest
				.tokenLocation("https://id.qlteacher.com/api/auth/token?grant_type=password")
				.setGrantType(GrantType.PASSWORD).setClientId(Constant.clientId)
				.setClientSecret(Constant.cientSecret).setUsername(Constant.userName)
				.setPassword(Constant.password).buildBodyMessage();
		OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(request, OAuth.HttpMethod.POST);
		String accessToken = oAuthResponse.getAccessToken();
		
		System.out.println("accessToken="+accessToken);
		
		return accessToken;
	}
	
	public static void main(String[] args) throws OAuthSystemException, OAuthProblemException {
		getAccessToken();
	}

}
