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

public class GetStatusDemo {

    public static void main(String[] args) throws OAuthSystemException, OAuthProblemException {

        //读取上传任务详细信息
        //全部
        getStateResult(Constant.PROJECT_ID + "/uploadmessage/" + "total");

        //待处理
        getStateResult(Constant.PROJECT_ID + "/uploadmessage/" + "wait");

        //处理中
        getStateResult(Constant.PROJECT_ID + "/uploadmessage/" + "process");

        //处理成功
        getStateResult(Constant.PROJECT_ID + "/uploadmessage/" + "success");

        //读取上传任务错误列表
        getStateResult(Constant.PROJECT_ID + "/errormessage");

        //读取单条上传作品状态
        //作品id
        int hkId = 1234;
        getStateResult(Constant.PROJECT_ID + "/hk/" + hkId);

        //读取整体处理状态（旧）
        getStateResult(Constant.PROJECT_ID);
    }

    public static void getStateResult(String tailUrl) throws OAuthSystemException, OAuthProblemException {

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
                Constant.BASE_URL + tailUrl)
                .setAccessToken(AccessTokenDemo.getAccessToken())
                .buildHeaderMessage();

        OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
                OAuthResourceResponse.class);

        System.out.println(resourceResponse.getBody());
    }

}
