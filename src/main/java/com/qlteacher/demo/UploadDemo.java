package com.qlteacher.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qlteacher.demo.doman.OtoUploadHkInfoVO;
import com.qlteacher.demo.doman.OtoUploadHkItemVO;

public class UploadDemo {

	public static void main(String[] args) throws JsonProcessingException{
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		
		OtoUploadHkInfoVO vo = new OtoUploadHkInfoVO();
		vo.setAuthorIdCard("身份证号");
		vo.setCourseId("31_549bdac796930d5bdd5fdb58");
		vo.setCreatedTime(new Date());
		vo.setCreateTime(new Date());
		vo.setDelete(false);
		vo.setHkAppraisedConfId(Constant.projectId);
		vo.setHkPartConfId(Constant.partConf);
		vo.setLastUpdatetime(new Date());
		vo.setPrizeLevel(2);
		vo.setThumbnailUrl("http://封面.jpg");
		vo.setTitle("测试接口");
		vo.setUpdatedTime(new Date());
		vo.setLastUpdatetime(new Date());
		vo.setId("233");
		
		
		List<OtoUploadHkItemVO> list = new ArrayList<OtoUploadHkItemVO>();
		OtoUploadHkItemVO oi = new OtoUploadHkItemVO();
		oi.setContent("http://上课实录.mp4");
		oi.setFileName("上课实录.mp4");
		oi.setIndex(1);
		oi.setStep(1);
		oi.setTitle("上课实录");
		oi.setType(1);
		
		list.add(oi);

		OtoUploadHkItemVO oi2 = new OtoUploadHkItemVO();
		oi2.setContent("http://课件设计.rar");
		oi2.setFileName("课件设计.rar");
		oi2.setIndex(1);
		oi2.setStep(2);
		oi2.setTitle("课件设计");
		oi2.setType(6);
		list.add(oi2);

		vo.setContentItems(list);
		
		OtoUploadHkInfoVO[] a = new OtoUploadHkInfoVO[1];
		a[0]=vo;
		
		ObjectMapper mapper = new ObjectMapper();
		
		String json = mapper.writeValueAsString(a);

		System.out.println(json);
		

		OAuthClientRequest bearerClientRequest;
		try {
			bearerClientRequest = new OAuthBearerClientRequest(
					"https://api.qlteacher.com/oauth2/otoel/upload").setAccessToken(AccessTokenDemo.getAccessToken())
							.buildHeaderMessage();
			
			bearerClientRequest.setHeader("Content-Type", "application/json");
			bearerClientRequest.setBody(json);
			
			OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.POST,
					OAuthResourceResponse.class);

			System.out.println(resourceResponse.getBody());
		} catch (OAuthSystemException | OAuthProblemException e) {
			//这里如果后台返回异常 通过oltu包是读不到异常信息的,请自行打断点看 URLConnectionClient的httpURLConnection.getErrorStream()
			e.printStackTrace();
		}

	}
}
