package com.qlteacher.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qlteacher.demo.doman.Constant;
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

    public static void main(String[] args) {

        // 上传作品
        uploadHkInfo();

        // 删除作品
//        deleteHkInfo();
    }

    /**
     * 删除作品，所列属性都是必填的
     */
    private static void deleteHkInfo() {

        // 作品对象
        OtoUploadHkInfoVO vo = new OtoUploadHkInfoVO();
        // 唯一标识
        vo.setId("001");
        // 作者身份证号
        vo.setAuthorIdCard("作者身份证号");
        // 作者姓名
        vo.setAuthorName("作者姓名");
        // 删除标志位
        vo.setDelete(true);
        // 年度
        vo.setHkAppraisedConfId(Constant.PROJECT_ID);

        OtoUploadHkInfoVO[] hkList = new OtoUploadHkInfoVO[1];
        hkList[0] = vo;

        callUploadHkInfo(hkList);
    }

    /**
      * 上传作品，所列属性都是必填的
      */
    private static void uploadHkInfo() {

        // 作品对象
        OtoUploadHkInfoVO vo = new OtoUploadHkInfoVO();
        // 唯一标识
        vo.setId("001");
        // 标题
        vo.setTitle("测试作品001");
        // 作者身份证号
        vo.setAuthorIdCard("作者身份证号");
        // 作者姓名
        vo.setAuthorName("作者姓名");
        // 课程id，通过接口获得
        vo.setCourseId("31_549bdac796930d5bdd5fdb58");
        // 删除标志位
        vo.setDelete(false);
        // 年度
        vo.setHkAppraisedConfId(Constant.PROJECT_ID);
        // 学段
        vo.setHkPartConfId(Constant.PART_CONF);
        // 排名
        vo.setRanking(2);
        // 缩略图
        vo.setThumbnailUrl("http://封面.jpg");

        // 作品项列表
        List<OtoUploadHkItemVO> list = new ArrayList<>();
        OtoUploadHkItemVO oi = new OtoUploadHkItemVO();
        // 步骤 小学初中高中1~10,幼儿园1~7
        oi.setStep(1);
        // 步骤内排序
        oi.setIndex(1);
        // 标题
        oi.setTitle("上课实录");
        // 下载地址
        oi.setContent("http://上课实录.mp4");
        // 文件名、带扩展名
        oi.setFileName("上课实录.mp4");
        list.add(oi);

        OtoUploadHkItemVO oi2 = new OtoUploadHkItemVO();
        oi2.setStep(2);
        oi2.setIndex(1);
        oi2.setTitle("课件设计");
        oi2.setContent("http://课件设计.rar");
        oi2.setFileName("课件设计.rar");
        list.add(oi2);

        vo.setContentItems(list);

        OtoUploadHkInfoVO[] hkList = new OtoUploadHkInfoVO[1];
        hkList[0] = vo;

        callUploadHkInfo(hkList);
    }


    public static void callUploadHkInfo(OtoUploadHkInfoVO[] hklist) {

        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

        ObjectMapper mapper = new ObjectMapper();

        String json = "";
        try {
            json = mapper.writeValueAsString(hklist);
        } catch (JsonProcessingException jpe) {
            jpe.printStackTrace();
        }

        // 打印输出json
        System.out.println(json);

        OAuthClientRequest bearerClientRequest;
        try {
            bearerClientRequest = new OAuthBearerClientRequest(
                    Constant.BASE_URL + "upload").setAccessToken(AccessTokenDemo.getAccessToken())
                    .buildHeaderMessage();

            bearerClientRequest.setHeader("Content-Type", "application/json");
            bearerClientRequest.setBody(json);

            OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.POST,
                    OAuthResourceResponse.class);

            System.out.println(resourceResponse.getBody());

        } catch (OAuthSystemException | OAuthProblemException e) {

            // 这里如果后台返回异常 通过oltu包是读不到异常信息的,请自行打断点看 URLConnectionClient的httpURLConnection.getErrorStream()
            e.printStackTrace();
        }
    }
}
