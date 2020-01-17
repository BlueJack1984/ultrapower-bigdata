package com.example.admin.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 解决方案输入
 * @author daniel
 * @date 2019-01-07
 */
@Data
public class CorporationInput implements Serializable {

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空")
    private String corporationName;
    /**
     * 统一社会信用代码
     */
    @NotBlank(message = "统一社会信用代码不能为空")
    private String registrationCode;
}
