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

		//读取上传任务详细信息
		//全部
		getStateResult(Constant.projectId + "/uploadmessage/" + "total");

		//待处理
		getStateResult(Constant.projectId + "/uploadmessage/" + "wait");

		//处理中
		getStateResult(Constant.projectId + "/uploadmessage/" + "process");

		//处理成功
		getStateResult(Constant.projectId + "/uploadmessage/" + "success");


		//读取上传任务错误列表
		getStateResult(Constant.projectId + "/errormessage");

		//读取单条上传作品状态
		//作品id
		int hkId = 1234;
		getStateResult(Constant.projectId + "/hk/" + hkId);

		//读取整体处理状态（旧）
		getStateResult(Constant.projectId);
	}

	public static void getStateResult(String tailUrl) throws OAuthSystemException, OAuthProblemException {

		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
				"http://api.qlteacher.com/oauth2/otoel/" + tailUrl)
				.setAccessToken(AccessTokenDemo.getAccessToken())
				.buildHeaderMessage();

		OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET,
				OAuthResourceResponse.class);

		System.out.println(resourceResponse.getBody());
	}

}
