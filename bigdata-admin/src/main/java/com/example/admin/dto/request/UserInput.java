package com.example.admin.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 解决方案输入
 * @author daniel
 * @date 2019-01-07
 */
@Data
public class UserInput implements Serializable {

    /**
     * 用户类型：企业用户，数据审核员，管理员
     */
    @NotNull(message = "用户类型信息不能为空")
    private Integer targetType;
    /**
     * 账号
     */
    @Length(min = 2, max = 50, message = "用户账号不符合长度要求")
    @NotBlank(message = "用户账号不能为空")
    private String account;
    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空")
    private String realName;
    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空")
    private String corporationName;
    /**
     * 公司职位
     */
    @NotBlank(message = "公司职位不能为空")
    private String position;
    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    private String email;
    /**
     * 个人名片
     */
    @NotEmpty(message = "个人名片url地址集合不能为空")
    private List<String> businessCardUrlList;
    /**
     * 营业执照
     */
    @NotEmpty(message = "营业执照url地址集合不能为空")
    private List<String> businessLicenseUrlList;
}
