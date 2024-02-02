package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AccountRequestBody;

import static io.restassured.RestAssured.given;

public class GetUserRequest {
    public static Response getUser(String userUUID, String token) {
        System.out.println("----------------------------------");
        System.out.println("getUser");
        System.out.println("userUUID: " + userUUID);
        System.out.println("token: " + token);
        System.out.println("----------------------------------");

        return executeGetUserPostRequest(userUUID, token);
    }

    public static Response executeGetUserPostRequest(String userUUID, String token) {
        System.out.println("----------------------------------");
        System.out.println("executeGetUserPostRequest");
        System.out.println("userUUID: " + userUUID);
        System.out.println("token: " + token);
        System.out.println("----------------------------------");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .get("User/" + userUUID);
    }
}
