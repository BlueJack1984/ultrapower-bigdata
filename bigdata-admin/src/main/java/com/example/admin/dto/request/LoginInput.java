package com.example.admin.dto.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import sun.security.util.ManifestEntryVerifier;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录信息输入
 * @author daniel
 * @date 2019-01-07
 */
@Data
public class LoginInput implements Serializable {

    @NotBlank(message = "")
    private String account;
    @NotBlank(message = "")
    @Length(message = "")
    private String password;
}
