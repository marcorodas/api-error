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


    public String getTrace(String packageFilter) {
        if (trace == null) {
            return null;
        }
        String[] split = trace.split("\n");
        StringBuilder builder = new StringBuilder("\t\t");
        if (packageFilter == null) {
            builder.append(split[0].trim());
            for (int i = 1; i < split.length; i++) {
                builder.append("\n\t\t").append(split[i].trim());
            }
        } else {
            String item = split[0].trim();
            if (this.check(item, packageFilter)) {
                builder.append(item);
            }
            for (int i = 1; i < split.length; i++) {
                item = split[i].trim();
                if (this.check(item, packageFilter)) {
                    builder.append("\n\t\t").append(item);
                }
            }
        }
        return builder.toString();
    }

    private boolean check(String item, String filter) {
        return !item.startsWith("at") || item.startsWith("at " + filter);
    }

}