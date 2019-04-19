package com.qlteacher.demo.doman;

import java.util.Date;

import lombok.Data;

@Data
public class OtoUploadHkItemVO {

	private String id;
	
	/**
     * 创建时间
     */
	private Date createdTime = new Date();

    /**
     * 最后更新时间
     */
	private Date updatedTime = new Date();

	/** @Fields title : 标题 */
	private String title;

	/** @Fields step : 部分 */
	private int step = 1;

	/** @Fields index : 部分内排序 */
	private int index = 1;

	/** @Fields content : 内容 */
	private String content;

	/** @Fields fileName : 文件名 */
	private String fileName;

	/** @Fields type : 类型 */
	private int type;

}
