package com.example.admin.handler;

import com.example.admin.dto.response.OutputResult;
import com.example.core.exception.ApplicationException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 全局统一异常处理
 * @author daniel
 * @date 2019-12-30
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public OutputResult<Void> handle() {
        return null;
    }

    /**
     * 处理@Valid注解产生的异常信息，转换为统一异常返回
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public OutputResult<Void> MyExceptionHandle(MethodArgumentNotValidException exception){

        exception.printStackTrace();
        BindingResult result = exception.getBindingResult();
        StringBuilder errorMsg = new StringBuilder() ;

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            fieldErrors.forEach(error -> {
                System.out.println("field" + error.getField() + ", msg:" + error.getDefaultMessage());
                errorMsg.append(error.getDefaultMessage()).append("!");
            });
        }

        exception.printStackTrace();
        return new OutputResult(-1,errorMsg.toString() );
    }
    /**
     * 拦截捕捉参数校验异常 MethodArgumentNotValidException.class
     * @param ex
     * @return
     */
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public OutputError methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
//        return new OutputError(ApplicationException.PARAM_VALIDATION_ERROR, ex.getMessage());
//    }
//    /**
//     * 拦截捕捉参数类型转换异常 HttpMessageConversionException.class
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = HttpMessageConversionException.class)
//    public OutputError httpMessageConversionExceptionHandler(HttpMessageConversionException ex) {
//        return new OutputError(ApplicationException.PARAM_CONVERSION_ERROR, "参数类型转换异常");
//    }
//
//    /**
//     * 拦截捕捉账号错误异常 UnknownAccountException.class
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = UnknownAccountException.class)
//    public OutputError unknownAccountExceptionHandler(UnknownAccountException ex) {
//        return new OutputError(ApplicationException.SHIRO_ACCOUNT_UNKNOWN, "用户认证账号不存在");
//    }
//
//    /**
//     * 拦截捕捉密码错误异常 IncorrectCredentialsException.class
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = IncorrectCredentialsException.class)
//    public OutputError incorrectCredentialsExceptionHandler(IncorrectCredentialsException ex) {
//        return new OutputError(ApplicationException.SHIRO_CREDENTIALS_INCORRECT, "用户认证密码错误");
//    }
//    /**
//     * 拦截捕捉其他认证异常 UnauthorizedException.class
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = UnauthorizedException.class)
//    public OutputError unauthorizedExceptionHandler(UnauthorizedException ex) {
//        return new OutputError(ApplicationException.SHIRO_AUTHORITY_ERROR, "用户没有该访问权限");
//    }
//    /**
//     * 拦截捕捉无权限异常 AuthorizationException.class
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = AuthorizationException.class)
//    public OutputError authorizationExceptionHandler(AuthorizationException ex) {
//        return new OutputError(ApplicationException.SHIRO_AUTHORITY_ERROR, "用户权限异常");
//    }
//    /**
//     * 拦截捕捉其他权限异常 AuthenticationException.class
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = AuthenticationException.class)
//    public OutputError authenticationExceptionHandler(AuthenticationException ex) {
//        return new OutputError(ApplicationException.SHIRO_AUTHENTICATION_ERROR, ex.getMessage());
//    }
//
//
//    /**
//     * 拦截捕捉自定义异常 ApplicationException.class
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = ApplicationException.class)
//    public OutputError applicationExceptionHandler(ApplicationException ex) {
//        return new OutputError(ex.getCode(), ex.getMessage());
//    }
//    /**
//     * 全局异常捕捉处理
//     * @param ex
//     * @return
//     */
//    @ExceptionHandler(value = Exception.class)
//    public OutputError exceptionHandler(Exception ex) {
//        Throwable throwable = ex.getCause();
//        if (throwable != null && throwable.getCause() != null){
//            if (throwable.getCause() instanceof UndeclaredThrowableException){
//                UndeclaredThrowableException ut = (UndeclaredThrowableException)throwable.getCause();
//                Throwable throwable2 = ut.getUndeclaredThrowable();
//                if (throwable2 instanceof ApplicationException) {
//                    ApplicationException ae = (ApplicationException) throwable2;
//                    return new OutputError(ae.getCode(), ae.getMessage());
//                }
//            }
//        }
//        log.error("收到未知异常：{}", ex);
//        return new OutputError(ApplicationException.UNKNOWN_ERROR, "未知错误");
//    }
}
