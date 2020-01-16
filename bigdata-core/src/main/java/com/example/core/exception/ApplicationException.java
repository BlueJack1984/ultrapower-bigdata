package com.example.core.exception;

import com.example.core.constants.ResponseCode;
import lombok.Data;

/**
 * 自定义应用异常
 * @author daniel
 * @date 2019-12-23
 */
@Data
public class ApplicationException extends Exception{

    /**
     * 应用异常
     */
    private static final long serialVersionUID = 1L;
    public static final int SUCCEED = 200;
    /**
     * 管理员用户实体不存在code值设置
     */
    public static final int ADMIN_USER_NOT_EXISTS = 201;
    /**
     * 首页公告实体不存在code值设置
     */
    public static final int ANNOUNCEMENT_NOT_EXISTS = 202;
    /**
     * 权限实体不存在code值设置
     */
    public static final int AUTHORITY_NOT_EXISTS = 203;
    /**
     * 部门实体不存在code值设置
     */
    public static final int DEPARTMENT_NOT_EXISTS = 204;
    /**
     * 留言实体不存在code值设置
     */
    public static final int MESSAGE_NOT_EXISTS = 205;
    /**
     * 个人积分实体不存在code值设置
     */
    public static final int PERSONAL_BONUS_NOT_EXISTS = 206;
    /**
     * 职位实体不存在code值设置
     */
    public static final int POSITION_NOT_EXISTS = 207;
    /**
     * 会员等级实体不存在code值设置
     */
    public static final int RANK_NOT_EXISTS = 208;
    /**
     * 角色实体不存在code值设置
     */
    public static final int ROLE_NOT_EXISTS = 209;
    /**
     * 股票证券实体不存在code值设置
     */
    public static final int STOCK_NOT_EXISTS = 210;
    /**
     * 系统积分实体不存在code值设置
     */
    public static final int SYSTEM_BONUS_NOT_EXISTS = 211;
    /**
     * 会员用户实体不存在code值设置
     */
    public static final int MEMBER_USER_NOT_EXISTS = 212;
    /**
     * 消费记录实体不存在code值设置
     */
    public static final int CONSUME_RECORD_NOT_EXISTS = 213;

    /**
     * 角色权限关联实体不存在code值设置
     */
    public static final int ROLE_AUTHORITY_NOT_ASSOCIATES = 301;
    /**
     * 用户留言关联实体不存在code值设置
     */
    public static final int USER_MESSAGE_NOT_ASSOCIATES = 302;
    /**
     * 用户角色关联实体不存在code值设置
     */
    public static final int USER_ROLE_NOT_ASSOCIATES = 303;
    /**
     * 用户职位关联实体不存在code值设置
     */
    public static final int USER_POSITION_NOT_ASSOCIATES = 304;

    /**
     * JWT登录时签名令牌错误code值设置
     */
    public static final int JWT_SIGNATURE_ERROR = 401;
    /**
     * JWT登录的token令牌过期错误code值设置
     */
    public static final int JWT_EXPIRED_ERROR = 402;
    /**
     * JWT的token令牌格式错误code值设置
     */
    public static final int JWT_MALFORMED_ERROR = 403;
    /**
     * SHIRO通过用户账号认证时用户实体不存在code值设置
     */
    public static final int SHIRO_ACCOUNT_UNKNOWN = 404 ;
    /**
     * SHIRO认证时登录密码错误code值设置
     */
    public static final int SHIRO_CREDENTIALS_INCORRECT = 405 ;
    /**
     * SHIRO其他认证错误code值设置
     */
    public static final int SHIRO_AUTHENTICATION_ERROR = 406;
    /**
     * SHIRO授权错误code值设置
     */
    public static final int SHIRO_AUTHORITY_ERROR = 407;

    /**
     * 前端向后台传递参数的校验异常code值设置
     */
    public static final int PARAM_VALIDATION_ERROR = 501;
    /**
     * 参数转换过程的异常code值设置
     */
    public static final int PARAM_CONVERSION_ERROR = 502;

    /**
     * 登录时用户账号输入错误code值设置
     */
    public static final int ACCOUNT_PARAM_ERROR = 601;
    /**
     * 登录时图形验证码输入错误code值设置
     */
    public static final int CAPTCHA_PARAM_ERROR = 602;
    /**
     * 登录时用户密码输入错误code值设置
     */
    public static final int PASSWORD_PARAM_ERROR = 603;
    /**
     * 用户密码加密错误code值设置
     */
    public static final int PASSWORD_ENCRYPT_ERROR = 604;
    /**
     * 设置（更改）密码时新密码与确认密码不一致异常code值设置
     */
    public static final int PASSWORD_NEW_SURE_NOT_EQUAL = 605;
    /**
     * 更改密码时原密码与新密码相同-异常code值设置
     */
    public static final int PASSWORD_OLD_NEW_EQUAL = 606;
    /**
     * 修改超级密码权限错误code值设置
     */
    public static final int SUPER_PASSWORD_AUTHORITY_ERROR = 607;

    /**
     * 实体id参数传输错误code值设置
     */
    public static final int ENTITY_ID_PARAM_ERROR = 701;
    /**
     * 日期参数格式错误code值设置
     */
    public static final int DATE_PARAM_FORMAT_ERROR = 702;
    /**
     * 搜索关键词参数错误code值设置
     */
    public static final int KEYWORD_PARAM_ERROR = 703;
    /**
     * 系统积分一天内重复结算错误code值设置
     */
    public static final int DUPLICATE_CHECKOUT_ERROR = 704;
    /**
     * 通用参数错误code值设置
     */
    public static final int COMMON_PARAM_ERROR = 705;
    /**
     * 未知错误code值设置
     */
    public static final int UNKNOWN_ERROR = 706;
    /**
     * 登录状态错误code值设置
     */
    public static final int LOGIN_STATUS_ERROR = 707;


    private int code;
    private String message;

    public ApplicationException(int code) {
        super();
        this.code = code;
    }

    public ApplicationException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public ApplicationException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
