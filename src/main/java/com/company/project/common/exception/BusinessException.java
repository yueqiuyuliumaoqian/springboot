package com.company.project.common.exception;

import com.company.project.common.result.ResultCode;
import lombok.Data;
import org.springframework.util.StringUtils;


/**
 * Created with IntelliJ IDEA.
 * Description:
 *  业务异常类
 * @author LErry.li
 * Date: 2018-06-15
 * Time: 14:41
 */
@Data
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 2332608236621015980L;

    protected String code;

    protected String message;

    protected ResultCode resultCode;

    protected Object data;

    public BusinessException() {

    }

    public BusinessException(String message) {
        this();
        this.message = message;
    }

    public BusinessException(String format, Object... objects) {
        this();
        format = StringUtils.replace(format, "{}", "%s");
        this.message = String.format(format, objects);
    }

    public BusinessException(ResultCode resultCode, Object data) {
        this(resultCode);
        this.data = data;
    }

    public BusinessException(ResultCode resultCode) {
        this.resultCode = resultCode;
        this.code = resultCode.code().toString();
        this.message = resultCode.message();
    }

    public String getCode() {
        return code;
    }

    public BusinessException setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public BusinessException setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public Object getData() {
        return data;
    }

    public BusinessException setData(Object data) {
        this.data = data;
        return this;
    }
}
