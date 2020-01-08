package com.example.core.entity.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 提取大数据产品product，解决方案和成功案例通用属性
 * @author daniel
 * @date 2019-01-02
 */
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CommonModel extends BaseModel{

    /**
     * 逻辑分类，两类：大数据和工业互联网
     */
    private String logicType;
    /**
     * 所属公司
     */
    private Long corporationId;
    /**
     * 介绍：产品、应用案例、解决方案
     */
    private String introduction;
    /**
     * 名称：产品、应用案例、解决方案
     */
    private String name;
    /**
     * 版本号
     */
    private String version;
    /**
     * 简称：产品、应用案例、解决方案
     */
    private String abbreviation;
    /**
     * logo图标：产品、应用案例、解决方案
     */
    private String logoUrl;
    /**
     * 所属大类
     */
    private Long primaryCategoryId;
    /**
     * 所属小类
     */
    private Long secondaryCategoryId;
    /**
     * 基于的开源框架：多个id序列，逗号间隔组成字符串
     */
    private String basedFrameworks;
    /**
     * 核心功能
     */
    private String  coreFunctionality;
    /**
     * 详细描述
     */
    private String detailedDescription;
    /**
     * 地区
     */
    private String district;
    /**
     * 安全问题
     */
    private String safetyIssue;
    /**
     * 性能
     */
    private String performance;
    /**
     * 应用领域：多个id序列，逗号间隔组成字符串
     */
    private String applicationFieldIds;

}
