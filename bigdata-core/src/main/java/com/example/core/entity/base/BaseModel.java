package com.example.core.entity.base;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础对象
 * @author daniel
 * @date 2019-12-27
 */
@NoArgsConstructor
@Data
public class BaseModel implements Serializable {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 数据状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
}
