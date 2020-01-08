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
public class HotspotInput implements Serializable {

    /**
     * 常用@Valid，@Validation(@NotNull(非空),@NotBlank(字符串非空),@NotEmpty(字符串不0和集合不0))
     */
    @NotBlank(message = "密码不能为空")
    private String name;


}
