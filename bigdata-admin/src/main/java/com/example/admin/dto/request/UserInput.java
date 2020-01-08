package com.example.admin.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 解决方案输入
 * @author daniel
 * @date 2019-01-07
 */
@Data
public class UserInput implements Serializable {

    /**
     * 账号
     */
    @Length()
    @NotBlank(message = "")
    private String account;
}
