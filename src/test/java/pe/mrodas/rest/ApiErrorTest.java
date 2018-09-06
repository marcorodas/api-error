package pe.mrodas.rest;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

class ApiErrorTest {

    @Test
    void setTrace() throws IOException {
        InputStream inputStream = getClass().getClassLoader()
                .getResourceAsStream("exception.txt");
        Scanner scanner = new Scanner(inputStream);
        String trace = scanner.useDelimiter("\\A").next();
        scanner.close();
        inputStream.close();
        String filteredTrace = new ApiError().setTrace(trace)
                .getTrace("pe.mrodas");
        System.out.println(filteredTrace);
    }
}