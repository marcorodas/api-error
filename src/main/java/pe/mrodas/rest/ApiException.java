package pe.mrodas.rest;

public class ApiException extends Exception {

    private final ApiError apiError;

    public ApiError getApiError() {
        return apiError;
    }

    public ApiException(ApiError apiError) {
        super(apiError.getUserMessage());
        this.apiError = apiError;
    }
}