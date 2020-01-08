package com.example.core.entity;


import com.example.core.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 公司表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Corporation extends BaseModel {

    /**
     * 公司名称
     */
    private String name;
    /**
     * 注册国家
     */
    private String country;
    /**
     * 公司logo图标地址
     */
    private String logoUrl;
    /**
     * 法人代表
     */
    private String representative;
    /**
     * 注册资本
     */
    private String capital;
    /**
     * 注册资本币种
     */
    private String currencyType;
    /**
     * 注册日期
     */
    private String registrationDate;
    /**
     * 经营状况
     */
    private String businessCondition;
    /**
     * 从事行业，如能源、化工、电器等
     */
    private String domain;
    /**
     * 公司性质，如国企、私企、外资等
     */
    private String nature;
    /**
     * 公司所在地址
     */
    private String address;
    /**
     * 联系人
     */
    private String contactPerson;
    /**
     * 联系人手机号
     */
    private String phone;
    /**
     * 公司固定电话
     */
    private String telephone;
    /**
     * 公司邮箱
     */
    private String email;
    /**
     * 公司网址
     */
    private String website;
    /**
     * 公司介绍
     */
    private String introduction;
}
