package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AccountRequestBody;

import static io.restassured.RestAssured.given;

public class LoginUser {

    public static Response loginUser(String endpoint, AccountRequestBody accountRequestBody) {
        String requestBody = accountRequestBody.createAuthorizedUserRequestBody();
        return executeLoginUserPostRequest(endpoint, requestBody);
    }

    public static Response executeLoginUserPostRequest(String endpoint, Object requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }
}
