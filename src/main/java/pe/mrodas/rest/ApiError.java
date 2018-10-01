package pe.mrodas.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ApiError {

    private int statusCode;
    private String apiCode, userMessage, logMessage, stacktrace;

    public ApiError(int statusCode) {
        this.statusCode = statusCode;
    }

    public ApiError setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ApiError setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
        return this;
    }

    public ApiError setStacktrace(Exception e) {
        if (e == null) {
            stacktrace = null;
        } else {
            String[] traceLines = this.traceAsString(e)
                    .split("\n");
            stacktrace = this.addIndent(traceLines);
        }
        return this;
    }

    private String addIndent(String[] traceLines) {
        if (traceLines.length > 0) {
            StringBuilder builder = new StringBuilder("\t").append(traceLines[0]);
            for (int i = 1; i < traceLines.length; i++) {
                builder.append("\n\t").append(traceLines[i]);
            }
            return builder.toString();
        }
        return null;
    }

    private String traceAsString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String stackTrace = sw.toString();
        pw.close();
        return stackTrace;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public String getStacktrace(String packageFilter) {
        if (stacktrace == null) {
            return null;
        }
        String[] split = stacktrace.split("\n");
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

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getApiCode() {
        return this.apiCode;
    }

    public String getUserMessage() {
        return this.userMessage;
    }

    /**
     * Gets the log message.
     *
     * @return The message to log.
     */
    public String getLogMessage() {
        return this.logMessage;
    }

    public ApiError setApiCode(String apiCode) {
        this.apiCode = apiCode;
        return this;
    }

    public ApiError setUserMessage(String userMessage) {
        this.userMessage = userMessage;
        return this;
    }

    /**
     * Sets the message to log
     *
     * @param logMessage the log message
     * @return Same object
     */
    public ApiError setLogMessage(String logMessage) {
        this.logMessage = logMessage;
        return this;
    }

    public ApiError appendLogMessage(String logMessage) {
        this.logMessage = this.logMessage == null ? logMessage
                : this.logMessage.concat("\n").concat(logMessage);
        return this;
    }

    public ApiError prependLogMessage(String logMessage) {
        this.logMessage = this.logMessage == null ? logMessage
                : logMessage.concat("\n").concat(this.logMessage);
        return this;
    }

    public String toString() {
        String format = "\tstatusCode=%s, apiCode=%s, \n\tuserMessage=%s";
        String str = String.format(format, statusCode, apiCode, userMessage);
        if (logMessage != null) {
            str += ", logMessage=\n" + logMessage;
        }
        if (stacktrace != null) {
            str += ", stacktrace=\n" + stacktrace;
        }
        return String.format("%s(\n%s\n)", this.getClass().getSimpleName(), str);
    }
}