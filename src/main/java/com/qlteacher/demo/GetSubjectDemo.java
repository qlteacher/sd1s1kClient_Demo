package com.qlteacher.demo;

import com.qlteacher.demo.doman.Constant;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 * 获取课程目录接口示例
 *
 * @author lbxx
 * @date 2021/4/28
 */
public class GetSubjectDemo {

    public static void main(String[] args) {

        // 如需循环或递归调用或者整个列表，请在一次请求成功后，Sleep一下，防止程序跑死
        getSubject("0");
    }

    private static void getSubject(String id) {

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        try {
            OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                    Constant.SUBJECT_URL + id)
                    .setAccessToken(AccessTokenDemo.getAccessToken())
                    .buildHeaderMessage();

            OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
                    OAuthResourceResponse.class);

            System.out.println(resourceResponse.getBody());

        } catch (OAuthSystemException | OAuthProblemException e) {

            // 这里如果后台返回异常 通过oltu包是读不到异常信息的,请自行打断点看 URLConnectionClient的httpURLConnection.getErrorStream()
            e.printStackTrace();
        }

    }
}
