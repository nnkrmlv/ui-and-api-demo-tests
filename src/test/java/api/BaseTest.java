package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class BaseTest {
    private static final String BASE_URL = "https://reqres.in";

    @BeforeAll
    public static void setUrl() {
        RestAssured.baseURI = BASE_URL;
    }
}
