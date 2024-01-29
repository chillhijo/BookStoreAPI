package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AccountRequestBody;

import static io.restassured.RestAssured.given;

public class GenerateToken {

    public static Response generateToken(String endpoint, AccountRequestBody accountRequestBody) {
        String requestBody = accountRequestBody.createAuthorizedUserRequestBody();
        return executeGenerateTokenPostRequest(endpoint, requestBody);
    }

    public static Response executeGenerateTokenPostRequest(String endpoint, Object requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }
}
