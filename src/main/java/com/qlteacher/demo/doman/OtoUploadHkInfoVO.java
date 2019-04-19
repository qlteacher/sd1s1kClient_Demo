package com.qlteacher.demo.doman;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OtoUploadHkInfoVO {

	private String id;

	/**
	 * 创建时间
	 */
	private Date createdTime = new Date();

	/**
	 * 最后更新时间
	 */
	private Date updatedTime = new Date();

	/** @Fields thumbnailUrl : 缩略图 */
	private String thumbnailUrl = "";

	/** @Fields hkPartConfId : 作业版本Id */
	private String hkPartConfId = "";

	/** @Fields hkAppraisedConfId : 评选项目id */
	private String hkAppraisedConfId = "";

	/** @Fields title : 标题 */
	private String title = "";

	/** @Fields createTime : 创建时间 */
	private Date createTime = new Date();

	/** @Fields lastUpdatetime : 最后更新时间 */
	private Date lastUpdatetime = new Date();

	/** @Fields courseId : 课程id */
	private String courseId = "";

	/** @Fields authorIdCard : 作者身份证 */
	private String authorIdCard = "";

	/** @Fields isDelete : 标记删除(默认否) */
	private boolean isDelete = false;

	/**
	 * 奖项等级 一等奖 二等奖 三等奖
	 */
	private int prizeLevel;

	private List<OtoUploadHkItemVO> contentItems;

}
