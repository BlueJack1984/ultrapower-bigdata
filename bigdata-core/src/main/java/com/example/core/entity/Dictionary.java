package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 字典表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dictionary extends BaseModel {

    /**
     * 数据类别：0表示应用领域，1表示技术类型
     */
    private Integer targetType;
    /**
     * 数据名称
     */
    private String name;
    /**
     * 数据描述
     */
    private String description;
}
