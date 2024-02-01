package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AccountRequestBody;

import static io.restassured.RestAssured.given;

public class GetUserRequest {
    public static Response getUser(String userUUID, AccountRequestBody accountRequestBody) {
        String requestBody = accountRequestBody.createAuthorizedUserRequestBody();
        return executeGetUserPostRequest(userUUID, requestBody);
    }

    public static Response executeGetUserPostRequest(String userUUID, Object requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .get(userUUID);
    }
}
