package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 政策资讯表
 * @author daniel
 * @date 2019-12-27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PolicyInformation extends BaseModel {

    /**
     * 目标数据类型
     * 0表示政策，1表示资讯
     */
    private Integer targetType;
    /**
     * 发文机关名称
     */
    private String agencyName;
    /**
     * 发文机关类型
     * 0表示国家级，1表示地方
     */
    private Integer agencyType;
    /**
     * 标题
     */
    private String title;
    /**
     * 发布日期
     */
    private Date publishDate;
    /**
     * 媒体来源
     */
    private String mediaSource;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 目标数据内容
     */
    private String content;
    /**
     * 焦点图
     */
    private String focusImageUrl;
    /**
     * 原文链接
     */
    private String sourceDocumentUrl;
}
