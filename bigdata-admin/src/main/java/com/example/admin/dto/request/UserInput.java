package com.example.admin.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
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
    private Integer targetType;
    /**
     * 账号
     */
    @Length()
    @NotBlank(message = "")
    private String account;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 公司名称
     */
    private String corporationName;
    /**
     * 公司职位
     */
    private String position;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 个人名片
     */
    private List<String> businessCardUrlList;
    /**
     * 营业执照
     */
    private List<String> businessLicenseUrlList;
}
