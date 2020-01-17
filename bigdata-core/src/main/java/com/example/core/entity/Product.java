package com.example.core.entity;

import com.example.core.entity.base.BaseModel;
import com.example.core.entity.base.CommonModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 大数据产品表
 * @author daniel
 * @date 2019-12-27
 */
//@Document(indexName = "product")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Product extends CommonModel {

    /**
     * 解决方案：多个id序列，逗号间隔组成字符串
     */
    private String solutionIds;
    /**
     * 应用案例：多个id序列，逗号间隔组成字符串
     */
    private String applicationCaseIds;
    /**
     * 软件专利名称
     */
    private String softwarePatentName;

    /**
     * 软件专利申请日期
     */
    private Date softwarePatentApplicationDate;
    /**
     * 软件专利公布日期
     */
    private Date softwarePatentPublishDate;
    /**
     * 软件专利登记号
     */
    private String softwarePatentRegistrationNumber;
    /**
     * 大数据产品分类号
     */
    private String classificationNumber;
    /**
     * 大数据产品申请人
     */
    private String applicant;
    /**
     * 大数据产品平台认证状态
     */
    private String certificationStatus;
    /**
     * 大数据产品技术类型：多个id序列，逗号间隔组成字符串
     * 自主技术、原创、开源框架等
     */
    private String techniqueIds;
    /**
     * 大数据产品技术特征
     */
    private String techniqueFeature;

    /**
     * 大数据产品运行平台要求
     */
    private String platformRequirement;
    /**
     * 大数据产品销售情况
     */
    private String salesCondition;
}
