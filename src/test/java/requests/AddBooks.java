package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.BooksBody;

import static io.restassured.RestAssured.given;

public class AddBooks {

    public static Response addBooks(String token, BooksBody booksBody, String endpoint) {
        String requestBody = booksBody.createBooksRequestBody();
        return executeAddBooksRequest(token, requestBody, endpoint);
    }

    public static Response executeAddBooksRequest(String token, String requestBody, String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .post(endpoint);
    }
}
