package com.example.core.entity;

import com.example.core.entity.base.CommonModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 解决方案表
 * @author daniel
 * @date 2019-12-27
 */
//@Document(indexName = "solution")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Solution extends CommonModel {

    /**
     * 大数据产品：多个id序列，逗号间隔组成字符串
     */
    private String productIds;
    /**
     * 应用案例：多个id序列，逗号间隔组成字符串
     */
    private String applicationCaseIds;
    /**
     * 解决方案发布日期
     */
    private Date publishDate;
    /**
     * 解决方案平台认证状态
     */
    private String certificationStatus;
}
