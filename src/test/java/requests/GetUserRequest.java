package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AccountRequestBody;

import static io.restassured.RestAssured.given;

public class GetUserRequest {
    public static Response getUser(String userUUID, String token) {
        return executeGetUserPostRequest(userUUID, token);
    }

    public static Response executeGetUserPostRequest(String userUUID, String token) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .get("User/" + userUUID);
    }
}
