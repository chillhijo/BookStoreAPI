package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import payloads.Book;
import payloads.BooksBody;
import requests.*;
import responses.BooksListResponse;
import responses.LoginSuccessResponse;
import util.Constants;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.util.List;

public class AddBookToUser extends RestApiBase {
    public AddBookToUser() throws IOException {
    }
    /*
    Test flow:
    1. Ulogovati se
    2. pokupiti userId i token
    3. pozvati addBook i proslijediti poznati isbn od knjige

     */
    @Test
    public void addBookToUser() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response loginResponse = LoginUser.loginUser(
                bookstore_properties.getValue(Constants.ENDPOINT_LOGIN_USER),
                accountRequestBody);
        System.out.println("LOGIN");

        Response getListOfBooks = GetBooks.getBooks(
                bookstore_properties.getValue(Constants.ENDPOINT_BOOKS));

        Gson gson = new Gson();
        LoginSuccessResponse loginSuccessResponse = gson.fromJson(loginResponse.getBody().asString(), LoginSuccessResponse.class);

        BooksBody booksBody = new BooksBody();
        booksBody.setCollectionOfIsbn("9781449325862");

        Response bookResponse = AddBooks.addBooks(
                bookstore_properties.getValue(Constants.ENDPOINT_BOOKS),
                loginSuccessResponse.getToken(),
                booksBody);

        bookResponse.getBody().prettyPrint();
    }
}
