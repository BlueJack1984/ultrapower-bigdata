package com.example.core.entity;

import com.example.core.entity.base.CommonModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 公司表
 * @author daniel
 * @date 2019-12-27
 */
//@Document(indexName = "applicationCase")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ApplicationCase extends CommonModel {

    /**
     * 大数据产品：多个id序列，逗号间隔组成字符串
     */
    private String productIds;
    /**
     * 解决方案：多个id序列，逗号间隔组成字符串
     */
    private String solutionIds;
    /**
     * 应用案例合作方
     */
    private String partner;
    /**
     * 应用案例发布日期
     */
    private Date publishDate;
    /**
     * 逻辑架构图地址
     */
    private String architectureImageUrl;
    /**
     * 应用案例价值体现：实践意义
     */
    private String significance;
}
