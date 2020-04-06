package com.simtuitive.core.globalexception;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import com.simtuitive.core.common.Constants;

public class ErrorInfo {

    private final String url;

    private final String developerMessage;
    private final String userMessage;
    private final String exception;
    private final String stacktrace;

    public ErrorInfo(String url, Exception ex, boolean isStacktraceRequired) {
        this.url = url;
        this.developerMessage = ex.getLocalizedMessage();
        this.userMessage = ex.getLocalizedMessage();
        this.exception = ex.getClass().getCanonicalName();
        this.stacktrace = isStacktraceRequired ? ExceptionUtils.getStackTrace(ex)
                                               : null;
    }

    public ErrorInfo(String url, Exception ex, Integer status, boolean isStacktraceRequired) {
        this.url = url;
        // if internal server error we have to set generic error message for user message
        if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            this.userMessage = Constants.GENERIC_RUNTIME_ERROR_MESSAEGE;
        } else {
            this.userMessage = ex.getLocalizedMessage();
        }
        this.developerMessage = ex.getLocalizedMessage();
        this.exception = ex.getClass().getCanonicalName();
        this.stacktrace = isStacktraceRequired ? ExceptionUtils.getStackTrace(ex)
                                               : null;
    }

    public String getUrl() {
        return url;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getException() {
        return exception;
    }

    public String getStacktrace() {
        return stacktrace;
    }

}
