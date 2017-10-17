package com.jtsoft.letmedo.common;

public class DejiException extends Exception {
    private static final long serialVersionUID = 1L;
    private int errorCode;

    public DejiException() {
    }

    public DejiException(String detailMessage) {
        super(detailMessage);
    }

    public DejiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DejiException(Throwable throwable) {
        super(throwable);
    }

    public DejiException(int exceptionCode) {
        this.errorCode = exceptionCode;
    }

    public DejiException(int errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }

    public DejiException(int exceptionCode, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.errorCode = exceptionCode;
    }

    public DejiException(int exceptionCode, Throwable throwable) {
        super(throwable);
        this.errorCode = exceptionCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
