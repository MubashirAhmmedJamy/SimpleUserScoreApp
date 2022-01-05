package com.riseuplabs.SimpleUserScoreApp.pojo.response;

public class ApiResponse {
    public boolean success;
    public String message;
    public Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        setSuccess(true);
        this.data = data;
    }
}
