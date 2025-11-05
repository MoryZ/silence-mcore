package com.old.silence.mcore.result;


import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

import com.old.silence.mcore.message.McoreMessages;

/**
 * @author moryzang
 */
@Data
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -8013538827360371249L;

    private int code;
    private String message;
    private T data;
    private long timestamp;
    private String path;
    private String traceId; // 用于链路追踪

    // 常用状态码常量
    public static final int SUCCESS = 200;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int SERVICE_UNAVAILABLE = 503;

    private ApiResult() {
        this.timestamp = System.currentTimeMillis();
    }

    // ========== 成功响应 ==========
    public static <T> ApiResult<T> success() {
        return success(null);
    }

    public static <T> ApiResult<T> success(T data) {
        return success("操作成功", data);
    }

    public static <T> ApiResult<T> success(String message, T data) {
        return new ApiResult<T>()
                .setCode(SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    // ========== 失败响应 ==========
    public static <T> ApiResult<T> error(String message) {
        return error(INTERNAL_SERVER_ERROR, message);
    }

    public static <T> ApiResult<T> error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> ApiResult<T> error(int code, String message, T data) {
        return new ApiResult<T>()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }

    // ========== 预定义错误 ==========
    public static <T> ApiResult<T> badRequest(String message) {
        return error(BAD_REQUEST, message);
    }

    public static <T> ApiResult<T> unauthorized(String message) {
        return error(UNAUTHORIZED, message);
    }

    public static <T> ApiResult<T> forbidden(String message) {
        return error(FORBIDDEN, message);
    }

    public static <T> ApiResult<T> notFound(String message) {
        return error(NOT_FOUND, message);
    }

    public static <T> ApiResult<T> serviceUnavailable(String message) {
        return error(SERVICE_UNAVAILABLE, message);
    }

    // ========== 业务方法 ==========
    public boolean isSuccess() {
        return code >= 200 && code < 300;
    }

    public boolean isError() {
        return !isSuccess();
    }

    // 获取数据，如果失败则抛出异常
    public T getDataOrThrow() {
        if (isSuccess()) {
            return data;
        }
        throw McoreMessages.COMMON_SERVER_ERROR.createException(message);
    }

    // 获取数据，如果失败返回默认值
    public T getDataOrDefault(T defaultValue) {
        return isSuccess() ? data : defaultValue;
    }

    // ========== Builder 模式 ==========
    public static <T> ApiResultBuilder<T> builder() {
        return new ApiResultBuilder<>();
    }

    public static class ApiResultBuilder<T> {
        private int code = SUCCESS;
        private String message = "success";
        private T data;
        private final long timestamp = System.currentTimeMillis();
        private String path;
        private String traceId;

        public ApiResultBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public ApiResultBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ApiResultBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ApiResultBuilder<T> path(String path) {
            this.path = path;
            return this;
        }

        public ApiResultBuilder<T> traceId(String traceId) {
            this.traceId = traceId;
            return this;
        }

        public ApiResult<T> build() {
            ApiResult<T> result = new ApiResult<>();
            result.setCode(code);
            result.setMessage(message);
            result.setData(data);
            result.setTimestamp(timestamp);
            result.setPath(path);
            result.setTraceId(traceId);
            return result;
        }
    }
}