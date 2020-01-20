package com.example.admin.controller.unit;

import com.example.admin.dto.response.OutputResult;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 企业申请信息的审核模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="企业申请信息的审核模块", tags = {"企业申请信息的审核模块:AuditController"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {

    /**
     * 根据id获取特定公司信息
     * @param id 公司id
     * @return 返回公司信息
     */
    @GetMapping("")
    public OutputResult<Void> audit() {
        return null;
    }

    /**
     * 企业用户申请
     * @param id 公司id
     * @return 返回公司信息
     */
    /**
     * 管理员审核
     * @param id 公司id
     * @return 返回公司信息
     */
    /**
     * 管理员认证
     * @param id 公司id
     * @return 返回公司信息
     */
}
