package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 类别表（大类和小类）
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Category extends BaseModel {

    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别描述
     */
    private String description;
    /**
     * 上一级类别id
     * 父类id
     */
    private Long parentId;
}
