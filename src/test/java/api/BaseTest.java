package api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


/**
 * This Base Class is intended to show that base url can be set globally.
 * It works by extending every test with BaseTest.
 **/
public class BaseTest {
    private static final String BASE_URL = "https://reqres.in";

    @BeforeAll
    public static void setUrl() {
        RestAssured.baseURI = BASE_URL;
    }
}
