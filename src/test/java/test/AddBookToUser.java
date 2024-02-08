package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import requests.*;
import responses.LoginSuccessResponse;
import responses.UserInfoResponse;
import util.Constants;

import java.io.IOException;
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
    public void addBookToUser() throws IOException {
        AccountRequestBody userBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response loginResponse = LoginUser.loginUser(
                bookstore_properties.getValue(Constants.ENDPOINT_LOGIN_USER),
                userBody);

        Gson gson = new Gson();
        LoginSuccessResponse loginSuccessResponse = gson.fromJson(loginResponse.getBody().asString(), LoginSuccessResponse.class);

        GetBooksTest getBooksTest = new GetBooksTest();
        List<String> booksList = getBooksTest.getBooksTest();

        Response userResponse = GetUser.getUser(
                bookstore_properties.getValue(Constants.ENDPOINT_USER),
                loginSuccessResponse.getUserId(),
                loginSuccessResponse.getToken());
//
//        UserInfoResponse userInfoResponse = gson.fromJson(userResponse.getBody().asString(), UserInfoResponse.class);
//        userInfoResponse.getBooks();
//
//        System.out.println("user response: " + userInfoResponse.getBooks());
        System.out.println("---------------------------");
        userResponse.getBody().prettyPrint();


//        Response bookList = GetBooks.getBooks(
//                bookstore_properties.getValue(Constants.ENDPOINT_BOOKS));
//        List<Book> listOfBooks = bookList.jsonPath().getList("isbn");
//        System.out.println("isbn of svih knjiga: " + listOfBooks);
//
//        BooksBody booksBody = new BooksBody();
//        booksBody.setUserId(userId);
    }
}
