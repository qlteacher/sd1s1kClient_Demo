package com.qlteacher.demo;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

public class getStatusDemo {
	
	public static void main(String[] args) throws OAuthSystemException, OAuthProblemException {
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
				"http://api.qlteacher.com/oauth2/otoel/"+Constant.projectId).setAccessToken(AccessTokenDemo.getAccessToken())
						.buildHeaderMessage();

		OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
				OAuthResourceResponse.class);

		System.out.println(resourceResponse.getBody());
	}

}
