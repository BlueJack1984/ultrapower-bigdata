package com.example.core.constants;

/**
 * 响应码枚举
 * @author daniel
 * @date 2019-12-23
 */
public enum ResponseCode {
    SUCCESS(200, ""),
    ERROR(201, ""),
    ILLEGAL(202, ""),

    USER_NOT_EXIST_ERROR(201, "用户不存在"),
    USER_PASSWORD_ERROR(202, "用户密码错误"),
    USER_INFO_STORE_ERROR(203, ""),
    USER_REGISTER_PHONE_ILLEGAL(204, "注册账号的手机号格式不合法"),
    USER_REGISTER_PHONE_ERROR(205, "填写的注册账号信息不是手机号"),
    USER_ACCOUNT_REGISTERED_ERROR(206, "该账号已经被注册"),
    USER_PASSWORD_CONFIRM_ERROR(207, "输入密码与确认密码不一致"),
    USER_CAPTCHA_ERROR(208, "验证码输入错误"),
    USER_REQUEST_PARAMETER_ERROR(209, "用户相关接口请求参数错误"),
    USER_ALREADY_EXIST_ERROR(210, "用户已经存在"),


    CORPORATION_NOT_EXIST_ERROR(300, "公司信息不存在"),
    CORPORATION_ALREADY_EXIST_ERROR(301, "公司信息已经存在"),


    IMAGE_NOT_EXIST_ERROR(400, "上传的图片不存在"),
    IMAGE_NAME_NOT_EXIST_ERROR(401, "上传的图片没有名称"),
    IMAGE_TYPE_ERROR(402, "上传文件不是图片类型"),
    IMAGE_IO_READ_ERROR(403, "无法读取上传图片的数据流"),
    IMAGE_SIZE_LIMIT_ERROR(404, "上传图片尺寸大小不符合要求"),
    IMAGE_STANDARD_NOT_EXIST_ERROR(405, "标准仓库中没有该图片类型的校验数据"),



    DOCUMENT_NOT_EXIST_ERROR(500, "上传的文档不存在"),
    DOCUMENT_NAME_NOT_EXIST_ERROR(501, "上传的文档没有名称"),
    DOCUMENT_FORMAT_ERROR(502, "上传的文档不是word或者pdf格式"),
    DOCUMENT_STANDARD_NOT_EXIST_ERROR(503, "标准仓库中没有该文档类型的校验数据"),
    DOCUMENT_SIZE_LIMIT_ERROR(504, "上传文件大小超出最大限制"),
    DOCUMENT_IO_OPERATION_ERROR(505, "无法获取上传文档的数据流"),



    DATE_FORMAT_CONVERSION_ERROR(300, "Date日期格式转换错误")
    ;
    private Integer code;

    private String message;

    private ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }
    public String  getMessage() {
        return message;
    }

    /**
     * 类描述: 主要时用于返回错误码和错误信息
     * @author licanfeng
     * @description 返回码和msg
     * @date 2019/3/12
     * @return
     */
    public enum ResultStatusCode {
        OK(200, "OK"),
        HTTP_ERROR_100(100, "1XX错误"),
        HTTP_ERROR_300(300, "3XX错误"),
        HTTP_ERROR_400(400, "4XX错误"),
        HTTP_ERROR_500(500, "5XX错误"),
        SIGN_ERROR(120, "签名错误"),
        TIME_OUT(130, "访问超时"),
        KICK_OUT(300, "您已经在其他地方登录，请重新登录！"),
        BAD_REQUEST(407, "参数解析失败"),
        INVALID_TOKEN(401, "无效的授权码"),
        INVALID_CLIENTID(402, "无效的密钥"),
        REQUEST_NOT_FOUND(404, "访问地址不存在！"),
        METHOD_NOT_ALLOWED(405, "不支持当前请求方法"),
        REPEAT_REQUEST_NOT_ALLOWED(406, "请求重复提交"),
        SYSTEM_ERR(500, "服务器运行异常"),
        NOT_EXIST_USER_OR_ERROR_PWD(10000, "该用户不存在或密码错误"),
        NOT_PARAM_USER_OR_ERROR_PWD(10006, "用户名或密码为空"),
        LOGINED_IN(10001, "该用户已登录"),
        NOT_EXIST_BUSINESS(10002, "该商家不存在"),
        SHIRO_ERROR(10003, "登录异常"),
        UNAUTHO_ERROR(10004, "您没有该权限"),
        REDIS_ERROR(10006, "redis异常"),
        REDIS_CONNECT_ERROR(10007, "redis连接异常"),
        BIND_PHONE(10010, "请绑定手机号"),
        UPLOAD_ERROR(20000, "上传文件异常"),
        INVALID_CAPTCHA(30005, "无效的验证码"),
        USER_FROZEN(40000, "该用户已被冻结");

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        ResultStatusCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
