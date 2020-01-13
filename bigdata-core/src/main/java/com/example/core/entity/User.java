package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    /**
     * 用户类型
     * 0表示管理员用户，1表示企业用户
     */
    private Integer targetType;
    /**
     * 所属公司
     */
    private Long corporationId;
    /**
     * 所属公司名称
     */
    private String corporationName;
    /**
     * 所属公司职位
     */
    private String position;
    /**
     * 用户登录账号
     */
    private String account;
    /**
     * 用户登录密码
     */
    private String password;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String identityNumber;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像，存储url地址
     */
    private String headImageUrl;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 性别
     */
    private Integer gender;
    /**
     * 国家
     */
    private String country;
    /**
     * 省级
     */
    private String province;
    /**
     * 市级
     */
    private String city;
    /**
     * 地址
     */
    private String address;
    /**
     * 个人名片url地址
     */
    private String businessCardUrl;

}