package cn.xdf.hkmls.model;

import java.io.Serializable;

import cn.xdf.hkmls.enums.ErrorsEnum;
import cn.xdf.hkmls.util.context.ContextUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author daiyafei3@xdf.cn
 * @desc 公共响应对象
 * @classname Result
 * @date 2020/9/2
 */
@Getter
@Setter
public class ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = -7838133783469023081L;

    /**
     * 标识是否成功
     */
    private Boolean success;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 系统唯一标识
     */
    private String traceId;

    /**
     * 描述信息
     */
    private String message;

    /**
     * 响应对象
     */
    private T data;

    private ResponseEntity() {}

    /**
     * 返回默认 Result对象
     * @param
     * @return
     */
    public static ResponseEntity success() {
        ResponseEntity<Object> result = new ResponseEntity<>();
        result.setTraceId(ContextUtil.getTraceId());
        result.setSuccess(Boolean.TRUE);
        result.setMessage(ErrorsEnum.SUCCESS.getMessage());
        result.setCode(ErrorsEnum.SUCCESS.getCode());
        result.setData(null);
        return result;
    }

    public static <T> ResponseEntity<T> success(T data) {
        ResponseEntity<T> result = success();
        result.setData(data);
        return result;
    }

    public static <T> ResponseEntity<T> success(String message) {
        ResponseEntity<T> result = success();
        result.setMessage(message);
        return result;
    }

    public static <T> ResponseEntity<T> success(T data, String traceId) {
        ResponseEntity<T> result = success();
        result.setTraceId(traceId);
        result.setData(data);
        return result;
    }

    public static <T> ResponseEntity<T> success(T data, String traceId, String message) {
        ResponseEntity<T> result = success();
        result.setTraceId(traceId);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> ResponseEntity<T> fail(String message) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setTraceId(ContextUtil.getTraceId());
        result.setSuccess(Boolean.FALSE);
        result.setCode(ErrorsEnum.ERROR_SYSTEM.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> ResponseEntity<T> fail(ErrorsEnum error) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setTraceId(ContextUtil.getTraceId());
        result.setSuccess(Boolean.FALSE);
        result.setCode(error.getCode());
        result.setMessage(error.getMessage());
        return result;
    }

    public static <T> ResponseEntity<T> fail(ErrorsEnum error, String message) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setMessage(message);
        result.setSuccess(Boolean.FALSE);
        result.setCode(error.getCode());
        return result;
    }

    public static <T> ResponseEntity<T> fail(ErrorsEnum error, String traceId, String message) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setTraceId(traceId);
        result.setSuccess(Boolean.FALSE);
        result.setCode(error.getCode());
        result.setMessage(message);
        return result;
    }

    public static <T> ResponseEntity<T> fail(ErrorsEnum error, T data, String traceId, String message) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setTraceId(traceId);
        result.setSuccess(Boolean.FALSE);
        result.setCode(error.getCode());
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> ResponseEntity<T> fail(ErrorsEnum error, T data) {
        ResponseEntity<T> result = new ResponseEntity<>();
        result.setTraceId(ContextUtil.getTraceId());
        result.setSuccess(Boolean.FALSE);
        result.setCode(error.getCode());
        result.setMessage(error.getMessage());
        result.setData(data);
        return result;
    }

}
