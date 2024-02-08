package test;

import base.RestApiBase;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.Book;
import payloads.CollectionOfIsbns;
import requests.GetBooks;
import util.Constants;

import java.io.IOException;
import java.util.List;

public class GetBooksTest extends RestApiBase {
    public GetBooksTest() throws IOException {
    }

    @Test
    public void getBooksTest() {
        Response booksResponse = GetBooks.getBooks(
                bookstore_properties.getValue(Constants.ENDPOINT_BOOKS));
        System.out.println("List of books: " + booksResponse.getBody().prettyPrint());
    }
}
