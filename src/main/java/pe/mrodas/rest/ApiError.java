package pe.mrodas.rest;

public class ApiError {
    private String message;
    private String type;
    private int code;
    private int subCode;
    private String trace;

    public ApiError setMessage(String message) {
        this.message = message;
        return this;
    }

    public ApiError setType(String type) {
        this.type = type;
        return this;
    }

    public ApiError setCode(int code) {
        this.code = code;
        return this;
    }

    public ApiError setSubCode(int subCode) {
        this.subCode = subCode;
        return this;
    }

    public ApiError setTrace(String trace) {
        this.trace = trace;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    public int getSubCode() {
        return subCode;
    }

    public String getTrace() {
        return trace;
    }
}