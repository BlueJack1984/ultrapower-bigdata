package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 新闻表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class News extends BaseModel {

    /**
     * 所属公司id
     */
    private Long corporationId;
    /**
     * 新闻的目标类型
     */
    private Integer targetType;
    /**
     * 新闻的目标id
     */
    private Long targetId;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 媒体来源
     */
    private String mediaSource;
    /**
     * 新闻的发布日期
     */
    private Date publishDate;
    /**
     * 新闻内容
     */
    private String content;
    /**
     * 新闻的原文链接
     */
    private String sourceDocumentUrl;
}
