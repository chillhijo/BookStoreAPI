package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class GetBooks {

    public static Response getBooks(String endpoint) {
        return executeGetBooksRequest(endpoint);
    }

    public static Response executeGetBooksRequest(String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .get(endpoint);
    }
}
