package com.lyt.module.lawyerTeam;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:律师Bean
 * @author: hdj
 * @date: 2020-04-01 17:03:20
 */
public class TLawyerInfo implements Serializable {

	/** @Fields serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** 分页用参数 */
	private Integer start;
	private Integer length;
	private int draw;

	/** 主键 */
	private Long id;

	/** 律师账号 */
	private String lawyerAccount;

	/** 律师头像 */
	private String imageUrl;

	/** 性别 */
	private String sex;

	/** 民族 */
	private String nationName;

	/** 执业证号 */
	private String licenseNumber;

	/** 职业年限 */
	private int pYear;

	/** 所属地区 */
	private String areaCode;

	/** 所属地区 */
	private String areaName;

	/** 律所 */
	private String lawFirm;

	/** 擅长 */
	private String fieldName;

	/** 个人简介 */
	private String lawyerDescribe;

	/** 立即咨询是否点亮图标（1点亮 可咨询，0置灰 离线） */
	private int lightFlag;

	/** 律师姓名 */
	private String realName;

	/** 律师姓名 */
	private String lawyerName;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date updateTime;

	/** 操作人 */
	private String operator;

	/** 关联的律师团 */
	private String lawyerTeams;

	/** 律师状态0失效1有效 */
	private String status;

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLawyerAccount() {
		return lawyerAccount;
	}

	public void setLawyerAccount(String lawyerAccount) {
		this.lawyerAccount = lawyerAccount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNationName() {
		return nationName;
	}

	public void setNationName(String nationName) {
		this.nationName = nationName;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public int getpYear() {
		return pYear;
	}

	public void setpYear(int pYear) {
		this.pYear = pYear;
	}

	public String getLawFirm() {
		return lawFirm;
	}

	public void setLawFirm(String lawFirm) {
		this.lawFirm = lawFirm;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getLawyerDescribe() {
		return lawyerDescribe;
	}

	public void setLawyerDescribe(String lawyerDescribe) {
		this.lawyerDescribe = lawyerDescribe;
	}

	public int getLightFlag() {
		return lightFlag;
	}

	public void setLightFlag(int lightFlag) {
		this.lightFlag = lightFlag;
	}

	public String getLawyerName() {
		return lawyerName;
	}

	public void setLawyerName(String lawyerName) {
		this.lawyerName = lawyerName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLawyerTeams() {
		return lawyerTeams;
	}

	public void setLawyerTeams(String lawyerTeams) {
		this.lawyerTeams = lawyerTeams;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}