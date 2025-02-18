package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseTest {
    private static final String BASE_URL = "https://reqres.in";

    @BeforeAll
    public static void setUrl() {
        RestAssured.baseURI = BASE_URL;
    }
}
