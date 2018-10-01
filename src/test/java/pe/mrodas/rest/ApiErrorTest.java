package pe.mrodas.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

class ApiErrorTest {

    @Test
    void setTrace() throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("exception.txt");
        Scanner scanner = new Scanner(inputStream);
        String trace = scanner.useDelimiter("\\A").next();
        scanner.close();
        inputStream.close();
        String filteredTrace = new ApiError(HttpURLConnection.HTTP_UNAUTHORIZED)
                .setStacktrace(trace)
                .getStacktrace("pe.mrodas");
        System.out.println(filteredTrace);
    }
}