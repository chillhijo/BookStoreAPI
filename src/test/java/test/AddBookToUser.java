package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import payloads.Book;
import payloads.BooksBody;
import payloads.CollectionOfIsbns;
import requests.*;
import responses.LoginSuccessResponse;
import responses.UserInfoResponse;
import util.Constants;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


public class AddBookToUser extends RestApiBase {
    public AddBookToUser() throws IOException {
    }
    /*
    Test flow:
    1. Ulogovati se sa userom
    2. pokupiti userId i token
    3. pozvati addBook i proslijediti poznati isbn od knjige

     */
    @Test
    public void addBookToUser() throws IOException {
        AccountRequestBody userBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response loginResponse = LoginUser.loginUser(
                bookstore_properties.getValue(Constants.ENDPOINT_LOGIN_USER),
                userBody);

        Gson gson = new Gson();
        LoginSuccessResponse loginSuccessResponse = gson.fromJson(loginResponse.getBody().asString(), LoginSuccessResponse.class);

        Response booksResponse = GetBooks.getBooks(
                bookstore_properties.getValue(Constants.ENDPOINT_BOOKS));
        List<String> isbnList = booksResponse.jsonPath().getList("books.isbn");
        String oneIsbn = isbnList.get(1);

        CollectionOfIsbns singleIsbn = new CollectionOfIsbns(oneIsbn);
        List<CollectionOfIsbns> singleIsbnList = Collections.singletonList(singleIsbn);

        BooksBody booksBody = new BooksBody(
                loginSuccessResponse.getUserId(),
                singleIsbnList);

        Response addBookResponse = AddBooks.addBooks(
                loginSuccessResponse.getToken(),
                booksBody,
                bookstore_properties.getValue(Constants.ENDPOINT_BOOKS));
    addBookResponse.getBody().prettyPrint();
        System.out.println("Status code: " + addBookResponse.getStatusCode());
    }
}
