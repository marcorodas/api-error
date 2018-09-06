package pe.mrodas.rest;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        ArrayList<String> list = new ArrayList<String>();
        if (type != null) {
            list.add(String.format("\"type\":\"%s\"", type));
        }
        list.add(String.format("\"code\":%s, \"subcode\":%s", code, subCode));
        if (message != null) {
            list.add(String.format("\"message\":\"%s\"", message));
        }
        StringBuilder builder = new StringBuilder("\t").append(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            builder.append(",\n\t").append(list.get(i));
        }
        return String.format("{\n%s\n}", builder.toString());
    }
}