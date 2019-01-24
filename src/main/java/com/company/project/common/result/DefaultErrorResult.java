package com.company.project.common.result;

import cn.hutool.core.util.StrUtil;
import com.company.project.common.exception.BusinessException;
import com.company.project.common.util.RequestContextHolderUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *  默认全局错误返回结果
 * @author LErry.li
 * Date: 2018-06-15
 * Time: 14:41
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DefaultErrorResult implements Result {
    private static final long serialVersionUID = 1899083570489722793L;

    /**
     * HTTP响应状态码 {@link org.springframework.http.HttpStatus}
     */
    private Integer status;

    /**
     * HTTP响应状态码的英文提示
     */
    private String error;

    /**
     * 异常堆栈的精简信息
     *
     */
    private String message;

    /**
     * 我们系统内部自定义的返回值编码，{@link ResultCode} 它是对错误更加详细的编码
     *
     * 备注：spring boot默认返回异常时，该字段为null
     */
    private Integer code;

    /**
     * 调用接口路径
     */
    private String path;

    /**
     * 异常的名字
     */
    private String exception;

    /**
     * 异常的错误传递的数据
     */
    private Object errors;

    /**
     * 时间戳
     */
    private Date timestamp;

    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus, Object errors) {
        DefaultErrorResult result = DefaultErrorResult.failure(resultCode, e, httpStatus);
        result.setErrors(errors);
        return result;
    }

    public static DefaultErrorResult failure(ResultCode resultCode, Throwable e, HttpStatus httpStatus) {
        DefaultErrorResult result = new DefaultErrorResult();
        result.setCode(resultCode.code());
        result.setMessage(resultCode.message());
        result.setStatus(httpStatus.value());
        result.setError(httpStatus.getReasonPhrase());
        result.setException(e.getClass().getName());
        result.setPath(RequestContextHolderUtil.getRequest().getRequestURI());
        result.setTimestamp(new Date());
        return result;
    }

    public static DefaultErrorResult failure(BusinessException e) {
        DefaultErrorResult defaultErrorResult = DefaultErrorResult.failure(e.getResultCode() == null ? ResultCode.SUCCESS : e.getResultCode(), e, HttpStatus.OK, e.getData());
        if (StrUtil.isNotEmpty(e.getMessage())) {
            defaultErrorResult.setMessage(e.getMessage());
        }
        return defaultErrorResult;
    }

    public Integer getStatus() {
        return status;
    }

    public DefaultErrorResult setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public DefaultErrorResult setError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public DefaultErrorResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public DefaultErrorResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getPath() {
        return path;
    }

    public DefaultErrorResult setPath(String path) {
        this.path = path;
        return this;
    }

    public String getException() {
        return exception;
    }

    public DefaultErrorResult setException(String exception) {
        this.exception = exception;
        return this;
    }

    public Object getErrors() {
        return errors;
    }

    public DefaultErrorResult setErrors(Object errors) {
        this.errors = errors;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public DefaultErrorResult setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
