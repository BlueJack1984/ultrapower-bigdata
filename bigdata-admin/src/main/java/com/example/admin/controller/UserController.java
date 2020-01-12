package com.example.admin.controller;

import com.example.admin.dto.request.UserInput;
import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.entity.User;
import com.example.core.service.IUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="swagger测试", description="TestController")
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @ApiOperation(value="获取用户",notes="根据id获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="path", name = "id", value = "用户id", required = true, dataType = "Integer")
    })
    @GetMapping("/get/{id}")
    public User getById(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return user;
    }

    @ApiOperation(value = "用户登录功能实现", notes = "用户登录功能实现")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "LoginInput", name = "loginInput", value = "登录输入参数", required = true)})
    @CrossOrigin
    @GetMapping("/get/{id}/page")
    public OutputListResult<User> getByConditionPage() {

        PageInfo<User> pageInfo = null;
        return new OutputListResult<>(pageInfo);
    }

    @RequestMapping(value = "/searchInformationList", method = RequestMethod.POST)
    public OutputListResult<User> searchInformationList (
            //@RequestParam(value = "targetType", defaultValue = "-1") Integer targetType,
            @RequestParam("uid") Long uid,
            @RequestParam("type") Integer type,
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "dateType", required = false)Integer dateType,
            @RequestParam(value = "searchDateStart", required = false) String searchDateStart,
            @RequestParam(value = "searchDateEnd", required = false) String searchDateEnd,
            @RequestParam(value = "sourceCode", required = false) String sourceCode,
            @RequestParam(value = "operatorId", required = false)Long operatorId,
            //需要定义列表排序的类型，以及排序方式升序和降序
            @RequestParam(value = "orderParameter", required = false)Integer orderParameter,
            @RequestParam(value = "orderType", required = false)Integer orderType,
            //资讯页需要的参数
            @RequestParam(value = "categoryId",required = false) Long categoryId,
            @RequestParam(value = "status", required = false, defaultValue = "-1")Integer status,
            //活动页自己需要的参数,没有
            @RequestParam(value = "offSet",required = false, defaultValue = "1") Integer offSet,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") String pageSize) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", type);
        map.put("keywords", keywords);
        map.put("dateType", dateType);
        map.put("searchDateStart", searchDateStart);
        map.put("searchDateEnd", searchDateEnd);
        map.put("sourceCode", sourceCode);
        map.put("operatorId", operatorId);
        map.put("orderParameter", orderParameter);
        map.put("orderType", orderType);
        map.put("categoryId", categoryId);
        map.put("status", status);
        map.put("offSet", offSet);
        map.put("pageSize", pageSize);
        //调用service服务
        //List<User> list = userService.selectInformationList(map);
        List<User> list = null;
        return new OutputListResult(list);
    }

    /**
     * @author lushusheng
     * @param targetType 表示实体的类别
     * @param id 当前实体的id
     * @param reason 审核的理由说明
     * @param operation 需要对当前实体进行的操作
     *     operation:"submit" 表示提交审核操作
     *     operation:"unapprove" 表示审核不通过操作
     *     operation:"withdraw" 表示撤回操作
     *     operation:"approve" 表示审核通过操作
     *     operation:"publish" 表示发布操作
     *     operation:"approve&publish" 表示通过审核并发布操作
     * @param currentId 当前用户的id
     * @return 是否保存成功
     * @throws ApplicationException 保存异常
     */
    @ApiOperation(value = "对实体的各审核状态进行更改操作", notes = "根据targetType和实体的id对其审核状态进行更改操作")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "targetType", value = "实体类别", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "id", value = "实体的id", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "reason", value = "审核原因", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "operation", value = "审核操作", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "currentId", value = "用户id", required = true)
    })
    @RequestMapping(value = "audit", method = RequestMethod.GET)
    @CrossOrigin
    public OutputResult<Void> audit(
            @RequestParam(value = "targetType")Integer targetType,
            @RequestParam(value = "id")Long id,
            @RequestParam(value = "reason", required = false)String reason,
            @RequestParam(value = "operation")String operation,
            @RequestHeader("_current_id") Long currentId)throws ApplicationException {
        auditServer.audit(targetType, id, currentId, operation, reason);
        return new OutputResult<>();
    }

    /**
     *
     * @return
     */
    @PostMapping("/add")
    public OutputResult<User> add(@RequestBody @Valid UserInput userInput) {

        User user = new User();
        user.setTargetType(userInput.getTargetType());
        user.setAccount(userInput.getAccount());
        user.setRealName(userInput.getRealName());
        user.setCorporationName(userInput.getCorporationName());
        user.setPosition(userInput.getPosition());
        user.setEmail(userInput.getEmail());

        List<String> businessCardUrlList = userInput.getBusinessCardUrlList();
        List<String> businessLicenseUrlList = userInput.getBusinessLicenseUrlList();
        User storedUser = userService.add(user, businessCardUrlList, businessLicenseUrlList);
        return new OutputResult<>(storedUser);
    }

    @PostMapping("/information/modify")
    public OutputResult<User> modifyInformation(@RequestBody @Valid UserInput userInput) {


        return null;
    }

    @PostMapping("/password/modify")
    public OutputResult<User> modifyPassword(@RequestBody @Valid UserInput userInput) {


        return null;
    }

    @GetMapping("/password/reset")
    public OutputResult<User> resetPassword(@RequestBody @Valid UserInput userInput) {

        return null;
    }

    /**
     * 锁定用户，删除用户等功能
     * 更改数据库的status字段值实现
     * @return
     */
    @GetMapping("/status/modify")
    public OutputResult<User> modifyStatus() {


        return null;
    }
}
