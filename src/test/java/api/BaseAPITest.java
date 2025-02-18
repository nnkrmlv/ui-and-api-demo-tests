package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.time.Instant;
import static java.lang.StringTemplate.STR;



public class BaseAPITest extends BaseTest {

    @Test
    public void createNewUserSuccessfully() {
        String requestBodyCreate = STR."""
            {
              "email": "\{TestData.email}",
              "password": "\{TestData.generateRandomString()}"
            }
            """;
        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(requestBodyCreate)
                .when()
                    .post("/api/register")
                .then()
                    .assertThat().statusCode(HttpStatus.SC_OK)
                    .body("id", isA(Number.class))
                    .body("token", not(blankString()))
                    .extract().response();
        response.print();
    }

    @Test
    public void getExistedUserSuccessfully() {
        Response response = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                .when()
                .   get("/api/users/2")
                .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                    .body("data.first_name", not(blankString()))
                    .body("data.last_name", not(blankString()))
                    .body("data.email", containsString("@reqres.in"))
                    .extract().response();
        response.print();
    }

    @Test
    public void updateUserInfoSuccessfully() {
        String genName = TestData.generateRandomString();
        String genJob = TestData.generateRandomString();
        Instant timeBeforeRequest = Instant.now();
        String requestBodyUpdate = STR."""
            {
              "name": "\{genName}",
              "job": "\{genJob}"
            }
            """;
        String updatedAt = RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(requestBodyUpdate)
                .when()
                .   put("/api/users/2")
                .then()
                    .assertThat().statusCode(HttpStatus.SC_OK)
                    .body("name", equalTo(genName))
                    .body("job", equalTo(genJob))
                    .extract().jsonPath().getString("updatedAt");

        assertThat(Instant.parse(updatedAt), greaterThanOrEqualTo(timeBeforeRequest));
    }

    @Test
    public void createNewUserMissingPassword() {
        String requestBodyCreate = STR."""
            {
              "email": "\{TestData.email}"
            }
            """;
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBodyCreate)
                .when()
                .post("/api/register")
                .then()
                .assertThat().statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", equalTo("Missing password"))
                .extract().response();
        response.print();
    }
}
