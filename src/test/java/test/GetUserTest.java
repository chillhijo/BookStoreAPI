package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import requests.CreateUser;
import requests.GenerateToken;
import requests.GetUser;
import requests.LoginUser;
import responses.CreateUserResponse;
import responses.GenerateTokenResponses;
import responses.LoginSuccessResponse;
import util.Constants;
import util.RandomCredentialGenerator;

import java.io.IOException;

public class GetUserTest extends RestApiBase {
    public GetUserTest() throws IOException {
    }
    /*
    Test cases:
    1. login with already existing user, and get user info
    2. get info from new created user
     */

    @Test (priority = 1)
    private void getLogedUserInfo() {
        AccountRequestBody loginRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response loginResponse = LoginUser.loginUser(
                bookstore_properties.getValue(Constants.ENDPOINT_LOGIN_USER),
                loginRequestBody);

        Gson gson = new Gson();
        LoginSuccessResponse loginSuccessResponse = gson.fromJson(loginResponse.getBody().asString(), LoginSuccessResponse.class);

        Response userResponse = GetUser.getUser(
                loginSuccessResponse.getUserId(),
                loginSuccessResponse.getToken());

        userResponse.getBody().prettyPrint();
    }

    @Test (priority = 2)
    private void getCreatedUserInfo() {
        RandomCredentialGenerator generator = new RandomCredentialGenerator();
        String newUserName = generator.generateRandomUsername();
        String newPassword = generator.generateRandomPassword();
        AccountRequestBody newUserBody = new AccountRequestBody(
                newUserName, newPassword);
        System.out.println("user: " + newUserName);
        System.out.println("pass: " + newPassword);

        Response response = CreateUser.createUser(
                bookstore_properties.getValue(Constants.ENDPOINT_CREATE_USER),
                newUserBody);
        System.out.println("create user response below");
        response.getBody().prettyPrint();

        Response tokenResponse = GenerateToken.generateToken(
                bookstore_properties.getValue(Constants.ENDPOINT_GENERATE_TOKEN),
                newUserBody);
        System.out.println("TOKEN user response below");
        tokenResponse.getBody().prettyPrint();

        Gson gson = new Gson();
        CreateUserResponse userResponse = gson.fromJson(response.getBody().asString(), CreateUserResponse.class);
        GenerateTokenResponses tokenResponses = gson.fromJson(tokenResponse.getBody().asString(), GenerateTokenResponses.class);
        String userId = userResponse.getUserID();
        String userToken = tokenResponses.getToken();
        System.out.println("ID: " + userId);
        System.out.println("token: " + userToken);

        Response createdUserResponse = GetUser.getUser(userId, userToken);

        createdUserResponse.getBody().prettyPrint();
    }
}
