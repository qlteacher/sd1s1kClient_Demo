package com.qlteacher.demo.doman;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 上传作品
 */
@Data
public class OtoUploadHkInfoVO {

    /**
     * @Fields id : 唯一标识
     */
    private String id;

    /**
     * @Fields thumbnailUrl : 缩略图
     */
    private String thumbnailUrl;

    /**
     * @Fields hkPartConfId : 作业版本Id
     */
    private String hkPartConfId;

    /**
     * @Fields hkAppraisedConfId : 评选项目id
     */
    private String hkAppraisedConfId;

    /**
     * @Fields title : 标题
     */
    private String title;


    /**
     * @Fields courseId : 课程id
     */
    private String courseId;

    /**
     * @Fields authorIdCard : 作者身份证
     */
    private String authorIdCard;
    /**
     * @Fields authorIdCard : 作者身份证
     */
    private String authorName;

    /**
     * @Fields isDelete : 标记删除(默认否)
     */
    private boolean isDelete;

    /**
     * @Fields ranking : 排名
     */
    private int ranking;

    /**
     * @Fields contentItems :作品内容
     */
    private List<OtoUploadHkItemVO> contentItems;

}
