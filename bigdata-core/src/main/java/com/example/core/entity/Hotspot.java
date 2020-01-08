package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 热点云图表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Hotspot extends BaseModel {

    /**
     * 关联新闻id
     */
    private Long newsId;
    /**
     * 热点标签词汇
     * 多个词汇序列，逗号间隔组成字符串
     */
    private String labelVocabulary;
}
