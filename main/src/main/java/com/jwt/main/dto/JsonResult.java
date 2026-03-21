package com.jwt.main.dto;

public class JsonResult<T> {

    private boolean success;
    private String message;
    private T result;

    public JsonResult() {
    }

    public JsonResult(boolean success, String message, T result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static <T> JsonResult<T> success(String message, T result) {
        return new JsonResult<>(true, message, result);
    }

    public static <T> JsonResult<T> error(String message) {
        return new JsonResult<>(false, message, null);
    }

    public String getMessage() {
        return message;
    }

    public T getResult() {
        return result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(T result) {
        this.result = result;
    }
}