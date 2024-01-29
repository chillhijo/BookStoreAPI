package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AccountRequestBody;

import static io.restassured.RestAssured.given;

public class AuthorizedRequest {

    public static Response authorizedUser(String endpoint, AccountRequestBody accountRequestBody) {
        String requestBody = accountRequestBody.createAuthorizedUserRequestBody();
        return executeAuthorizedUserPostRequest(endpoint, requestBody);
    }

    public static Response executeAuthorizedUserPostRequest(String endpoint, Object requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(endpoint);
    }

}
