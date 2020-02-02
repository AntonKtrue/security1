package org.hyperskill.security.domain;

public class ResponseWrapper {

    private Status status;

    private Object data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResponseWrapper data(Object data) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus(new Status(Error.NO_ERRORS));
        responseWrapper.setData(data);
        return responseWrapper;
    }

    public static ResponseWrapper error(Error error) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus(new Status(error));
        return  responseWrapper;
    }


    public final static class Status {
        private int status;
        private int errorCode;
        private String errorComment;

        public Status(int status, int errorCode, String errorComment) {
            this.status = status;
            this.errorCode = errorCode;
            this.errorComment = errorComment;
        }

        public Status(Error error) {
            this.status = error.getStatus();
            this.errorCode = error.getErrorCode();
            this.errorComment = error.getErrorComment();
        }

        public int getStatus() {
            return status;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorComment() {
            return errorComment;
        }
    }

    public enum Error {
        NO_ERRORS(0, 0, null),
        AUTHENTICATION_ERROR(1, 1, "Authentication error!");

        private int status;
        private int errorCode;
        private String errorComment;

        Error(int status, int errorCode, String errorComment) {
            this.status = status;
            this.errorCode = errorCode;
            this.errorComment = errorComment;
        }

        public int getStatus() {
            return status;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorComment() {
            return errorComment;
        }
    }
}
