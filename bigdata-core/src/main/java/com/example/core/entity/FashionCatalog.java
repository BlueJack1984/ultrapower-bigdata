package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 新品速递表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FashionCatalog extends BaseModel {

    /**
     * 目标实体id
     */
    private Long targetId;
    /**
     * 目标实体类型
     */
    private Integer targetType;
    /**
     * 目标实体名称
     */
    private String name;
}
