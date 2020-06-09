package com.lyt.module.org.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @ClassName LegalAidOrgInfo
 * @Description 宁夏机构实体
 * @Author liyintao
 * @Date 2020/6/5 15:38
 */
public class OrgInfo implements Serializable {

    /** @Fields serialVersionUID */
    private static final long serialVersionUID = 1L;
    /**  */
    private String no;
    /** 机构名称 */
    private String name;
    /** 机构类型    公证处、法律援助中心、调解委员会    */
    private String type;
    /** 机构地址 */
    private String nameOfPath;
    /** 联系电话 */
    private String tel;
    /** 机构编码    需要解码 */
    private String encNo;
    /** 机构LOGO */
    private String logo;
    /** 机构 纬度 */
    private String mapx;
    /** 机构 经度 */
    private String mapy;
    /**  */
    private String objid;
    /** 领导人 */
    private String leader;
    /** 机构简介 */
    private String introduce;
    /**  */
    private String xydm;
    /**  */
    private String zyzh;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return StringUtils.isBlank(name) ? "" : name.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameOfPath() {
        return StringUtils.isBlank(nameOfPath) ? "" : nameOfPath.trim();
    }

    public void setNameOfPath(String nameOfPath) {
        this.nameOfPath = nameOfPath;
    }

    public String getTel() {
        return StringUtils.isBlank(tel) ? "" : tel.trim();
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEncNo() {
        return StringUtils.isBlank(encNo) ? "" : encNo.trim();
    }

    public void setEncNo(String encNo) {
        this.encNo = encNo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMapx() {
        return mapx;
    }

    public void setMapx(String mapx) {
        this.mapx = mapx;
    }

    public String getMapy() {
        return mapy;
    }

    public void setMapy(String mapy) {
        this.mapy = mapy;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public String getLeader() {
        return StringUtils.isBlank(leader) ? "" : leader.trim();
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getIntroduce() {
        return StringUtils.isBlank(introduce) ? "" : introduce.trim();
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getXydm() {
        return xydm;
    }

    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    public String getZyzh() {
        return zyzh;
    }

    public void setZyzh(String zyzh) {
        this.zyzh = zyzh;
    }
}
