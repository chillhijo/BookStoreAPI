package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetUser {
    public static Response getUser( String token, String endpoint,String userUUID) {
        return executeGetUserPostRequest(token, endpoint, userUUID);

    }

    public static Response executeGetUserPostRequest(String token, String endpoint,String userUUID) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token)
                .header("Accept", "application/json")
                .get(endpoint +"/"+ userUUID);
    }
}
