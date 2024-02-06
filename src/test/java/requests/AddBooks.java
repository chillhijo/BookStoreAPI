package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.BooksBody;

import static io.restassured.RestAssured.given;

public class AddBooks {

    public static Response addBooks(String endpoint, String token, BooksBody booksBody) {
        String requestBody = booksBody.createBooksRequestBody();
        return executeAddBooksRequest(endpoint, token, requestBody);
    }

    public static Response executeAddBooksRequest(String endpoint, String token, Object requestBody) {
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .post(endpoint);
    }
}
