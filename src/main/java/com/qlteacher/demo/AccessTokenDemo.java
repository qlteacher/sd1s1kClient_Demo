package com.qlteacher.demo;

import com.qlteacher.demo.doman.Constant;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;

public class AccessTokenDemo {


    public static void main(String[] args) {
        getAccessToken();
    }

    public static String getAccessToken() {
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        try {
            OAuthClientRequest request = OAuthClientRequest
                    .tokenLocation(Constant.OAUTH_URL)
                    .setGrantType(GrantType.PASSWORD).setClientId(Constant.CLIENT_ID)
                    .setClientSecret(Constant.CLIENT_SECRET).setUsername(Constant.USER_NAME)
                    .setPassword(Constant.USER_PASSWORD).buildBodyMessage();
            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(request, OAuth.HttpMethod.POST);
            String accessToken = oAuthResponse.getAccessToken();

//            System.out.println("accessToken=" + accessToken);

            return accessToken;
        } catch (OAuthSystemException | OAuthProblemException e) {

            // 这里如果后台返回异常 通过oltu包是读不到异常信息的,请自行打断点看 URLConnectionClient的httpURLConnection.getErrorStream()
            e.printStackTrace();
        }
        return "";
    }
}
