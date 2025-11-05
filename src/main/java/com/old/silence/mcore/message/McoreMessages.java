package com.old.silence.mcore.message;

import org.apache.http.HttpStatus;
import com.old.silence.core.context.ErrorCodedEnumMessageSourceResolvable;

/**
 * @author moryzang
 */
public enum McoreMessages implements ErrorCodedEnumMessageSourceResolvable {

    COMMON_SERVER_ERROR(HttpStatus.SC_INTERNAL_SERVER_ERROR, 51),;

    private final int httpStatusCode;
    private final int errorCode;

    McoreMessages(int httpStatusCode, int errorCode) {
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }
}
