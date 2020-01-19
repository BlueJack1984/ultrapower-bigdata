package com.example.admin.controller;

import com.example.admin.dto.request.UserInput;
import com.example.admin.dto.response.OutputListResult;
import com.example.admin.dto.response.OutputResult;
import com.example.core.constants.ResponseCode;
import com.example.core.entity.User;
import com.example.core.exception.ApplicationException;
import com.example.core.service.IUserService;
import com.example.core.utils.DESUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户模块
 * @author daniel
 * @date 2019-12-30
 */
@Api(value="用户模块", tags = {"用户模块接口"})
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    private final DESUtil desUtil;

    /**
     * 根据用户id获取单个用户信息
     * @param id 用户id
     * @return user信息
     */
    @ApiOperation(value="获取用户",notes="根据id获取用户")
    @ApiImplicitParams({ @ApiImplicitParam(paramType="path", name = "id", value = "用户id", required = true, dataType = "Long")})
    @GetMapping("/get/{id}")
    @CrossOrigin
    public OutputResult<User> getById(@PathVariable("id") Long id) throws ApplicationException{
        //查询用户
        //User user = null;
        User user = userService.getById(id);
        return new OutputResult<>(user);
    }

    /**
     * 根据用户id获取单个用户信息
     * @param searchDateStart 查询用户的创建时间起始点
     * @param searchDateEnd 查询用户的创建时间结束点
     * @param keyword 输入的用户账号参数
     * @param offSet 查询的页码
     * @param pageSize 每页显示的数据条数
     * @return user列表信息
     */
    @ApiOperation(value = "查询用户列表", notes = "多条件查询用户列表信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "searchDateStart", value = "查询用户的创建时间起始点", required = false),
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "searchDateEnd", value = "查询用户的创建时间结束点", required = false),
        @ApiImplicitParam(paramType = "query", dataType = "String", name = "keyword", value = "输入的用户账号", required = false),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "offSet", value = "查询的页码", required = false),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页显示的数据条数", required = false)
    })
    @CrossOrigin
    @GetMapping("/get/list/condition/page")
    public OutputListResult<User> getListByConditionPage(
            @RequestParam(value = "searchDateStart", required = false)String searchDateStart,
            @RequestParam(value = "searchDateEnd", required = false)String searchDateEnd,
            @RequestParam(value = "keyword", required = false)String keyword,
            @RequestParam(value = "offSet",required = false, defaultValue = "1") Integer offSet,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) throws ApplicationException{

        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("searchDateStart", searchDateStart);
        conditionMap.put("searchDateEnd", searchDateEnd);
        conditionMap.put("keyword", keyword);
        conditionMap.put("offSet", offSet);
        conditionMap.put("pageSize", pageSize);
        PageInfo<User> pageInfo = userService.getListByConditionPage(conditionMap);
        return new OutputListResult<>(pageInfo);
    }

    /**
     * 添加单个用户信息
     * @param userInput 添加用户的输入信息
     * targetType 用户类型：企业用户，数据审核员，管理员
     * account 账号
     * realName 真实姓名
     * corporationName 公司名称
     * position 公司职位
     * email 邮箱
     * businessCardUrlList 个人名片
     * businessLicenseUrlList 营业执照
     * @return 添加后的user信息，用于回显
     */
    @ApiOperation(value = "添加单个用户信息", notes = "添加单个用户信息")
    @ApiImplicitParams({ @ApiImplicitParam(paramType = "body", dataType = "UserInput", name = "userInput", value = "添加用户的信息实体", required = true)})
    @CrossOrigin
    @PostMapping("/add")
    public OutputResult<User> add(@RequestBody @Valid UserInput userInput) throws ApplicationException{

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

    /**
     * 修改单个用户信息
     * @param userInput 修改用户的输入信息
     * targetType 用户类型：企业用户，数据审核员，管理员
     * account 账号
     * realName 真实姓名
     * corporationName 公司名称
     * position 公司职位
     * email 邮箱
     * businessCardUrlList 个人名片
     * businessLicenseUrlList 营业执照
     * @return 返回修改后的user信息，用于回显
     */
    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "body", dataType = "UserInput", name = "userInput", value = "修改的用户信息", required = true)})
    @CrossOrigin
    @PostMapping("/modify/information/{id}")
    public OutputResult<User> modifyInformation(
            @PathVariable("id") Long id,
            @RequestBody UserInput userInput) throws ApplicationException{

        //判断账号参数
        String account = userInput.getAccount();
        if(StringUtils.isEmpty(account)) {
            log.error("【用户管理模块：修改用户信息接口-用户账号参数为空】");
            return new OutputResult<>(ResponseCode.USER_REQUEST_PARAMETER_ERROR);
        }
        //根据账号查询用户
        Map<String, Object> modifyMap = new HashMap<>();
        modifyMap.put("targetType", userInput.getTargetType());
        modifyMap.put("account", userInput.getAccount());
        modifyMap.put("realName", userInput.getRealName());
        modifyMap.put("corporationName", userInput.getCorporationName());
        modifyMap.put("position", userInput.getPosition());
        modifyMap.put("email", userInput.getEmail());
        modifyMap.put("businessCardUrlList", userInput.getBusinessCardUrlList());
        modifyMap.put("businessLicenseUrlList", userInput.getBusinessLicenseUrlList());

        User user = userService.modifyInformation(modifyMap);
        return new OutputResult<>(user);
    }

    /**
     * 修改用户密码信息
     * @param userId 用户id
     * @param originalPassword 用户原始密码
     * @param newPassword 新密码
     * @param confirmPassword 确认密码
     * @return 返回修改成功消息
     */
    @ApiOperation(value = "修改用户密码信息", notes = "修改用户密码信息")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "header", dataType = "Long", name = "userId", value = "用户id", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "String", name = "originalPassword", value = "用户原始密码", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "String", name = "newPassword", value = "新密码", required = true),
        @ApiImplicitParam(paramType = "body", dataType = "String", name = "confirmPassword", value = "确认密码", required = true)
    })
    @CrossOrigin
    @PostMapping("/modify/password")
    public OutputResult<Void> modifyPassword(
            @RequestHeader(value = "userId",required = true) Long userId,
            @RequestParam(value = "originalPassword",required = true) String originalPassword,
            @RequestParam(value = "newPassword",required = true) String newPassword,
            @RequestParam(value = "confirmPassword",required = true) String confirmPassword) throws ApplicationException{

        //判断数据是否为空
        if(StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(confirmPassword)) {
            log.error("【用户管理模块：修改密码接口-原始密码、新密码、确认密码至少有一个为空】");
            return new OutputResult<>(ResponseCode.USER_REQUEST_PARAMETER_ERROR);
        }
        //查询当前用户信息
        User user = userService.getById(userId);
        if(null == user) {
            log.error("【用户管理模块：修改密码接口-数据库中不存在当前用户】");
            return new OutputResult<>(ResponseCode.USER_NOT_EXIST_ERROR);
        }
        //判断原始密码是否相同
        String encryptedPassword = desUtil.encrypt(originalPassword);
        String storedPassword = user.getPassword();
        if(! encryptedPassword.equals(storedPassword)) {
            log.error("【用户管理模块：修改密码接口-原始密码输入错误】");
            return new OutputResult<>(ResponseCode.USER_PASSWORD_ERROR);
        }
        //判断新密码与确认密码是否相同
        if(! newPassword.equals(confirmPassword)) {
            log.error("【用户管理模块：修改密码接口-新密码与确认密码不一致】");
            return new OutputResult<>(ResponseCode.USER_PASSWORD_CONFIRM_ERROR);
        }
        String encryptedNewPassword = desUtil.encrypt(newPassword);
        //存入数据库中
        user.setPassword(encryptedNewPassword);
        user.setUpdateTime(new Date());
        userService.modifyById(user);
        //返回成功标志
        return new OutputResult<>();
    }

    /**
     * 重置用户密码信息
     * @param id 用户id
     * @return 返回修改成功消息
     */
    @ApiOperation(value = "重置用户密码信息", notes = "重置用户密码信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "url", dataType = "Long", name = "userId", value = "用户id", required = true)})
    @CrossOrigin
    @GetMapping("/reset/password/{id}")
    public OutputResult<User> resetPassword(@PathVariable(value = "id",required = true) Long id) throws ApplicationException{

        //查询用户信息
        User user = userService.getById(id);
        if(null == user) {
            log.error("【用户管理模块：重置密码接口-数据库中不存在当前用户】");
            return new OutputResult<>(ResponseCode.USER_NOT_EXIST_ERROR);
        }
        //设置默认密码
        String defaultPassword = "888888";
        String encryptedDefaultPassword = desUtil.encrypt(defaultPassword);
        user.setPassword(encryptedDefaultPassword);
        user.setUpdateTime(new Date());
        //将用户信息更新到数据库
        //userService.
        userService.modifyById(user);
        return new OutputResult<>();
    }


    /**
     * 修改用户状态信息，包括锁定，删除等功能
     * 更改数据库的status字段值实现
     * @param id 用户id
     * @param status 用户状态值
     * @return 返回修改成功消息
     */
    @ApiOperation(value = "修改用户状态信息", notes = "修改用户状态信息，包括锁定，删除等功能")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "用户id", required = true),
        @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "status", value = "用户状态值", required = true)
    })
    @CrossOrigin
    @GetMapping("/modify/status/{id}")
    public OutputResult<User> modifyStatus(
            @PathVariable(value = "id",required = true) Long id,
            @RequestParam(value = "status",required = true) Integer status) throws ApplicationException{

        //判断参数值
        if(null == status || status > 6 || status < 0) {
            log.error("【用户管理模块：修改用户状态接口-输入的用户状态值不合法】");
            return new OutputResult<>(ResponseCode.USER_REQUEST_PARAMETER_ERROR);
        }
        //调用服务
        User user = userService.getById(id);
        if(null == user) {
            log.error("【用户管理模块：修改用户状态接口-数据库中不存在当前用户】");
            return new OutputResult<>(ResponseCode.USER_NOT_EXIST_ERROR);
        }
        //更新数据库
        user.setStatus(status);
        user.setUpdateTime(new Date());

        userService.modifyById(user);
        return new OutputResult<>();
    }

}
