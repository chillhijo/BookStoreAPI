package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AccountRequestBody;

import static io.restassured.RestAssured.given;

public class CreateUserRequest {

    public static Response createUser(String endpoint, AccountRequestBody accountRequestBody) {
        String requestBody = accountRequestBody.createAuthorizedUserRequestBody();
        return executeCreateUserPostRequest(endpoint, requestBody);
    }

    public static Response executeCreateUserPostRequest(String endpoint, Object requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }
}
