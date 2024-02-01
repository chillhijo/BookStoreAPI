package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import requests.CreateUserRequest;
import requests.GenerateToken;
import requests.GetUserRequest;
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

    @Test
    private void getLogedUserInfo() {
        AccountRequestBody loginRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response loginResponse = LoginUser.loginUser(
                bookstore_properties.getValue(Constants.ENDPOINT_LOGIN_USER),
                loginRequestBody);

        Gson gson = new Gson();
        LoginSuccessResponse loginSuccessResponse = gson.fromJson(loginResponse.getBody().asString(), LoginSuccessResponse.class);

        Response userResponse = GetUserRequest.getUser(
                loginSuccessResponse.getUserId(),
                loginSuccessResponse.getToken());

        userResponse.getBody().prettyPrint();
    }

    @Test
    private void getCreatedUserInfo() {
        RandomCredentialGenerator generator = new RandomCredentialGenerator();
        String newUserName = generator.generateRandomUsername();
        String newPassword = generator.generateRandomPassword();

        AccountRequestBody newUserBody = new AccountRequestBody(
                newUserName, newPassword);

        Response response = CreateUserRequest.createUser(
                bookstore_properties.getValue(Constants.ENDPOINT_CREATE_USER),
                newUserBody);
        response.getBody().prettyPrint();

        Response tokenResponse = GenerateToken.generateToken(
                bookstore_properties.getValue(Constants.ENDPOINT_GENERATE_TOKEN),
                newUserBody);

        Gson gson = new Gson();
        CreateUserResponse userResponse = gson.fromJson(response.getBody().asString(), CreateUserResponse.class);
        GenerateTokenResponses tokenResponses = gson.fromJson(tokenResponse.getBody().asString(), GenerateTokenResponses.class);

        Response createdUserResponse = GetUserRequest.getUser(
                userResponse.getUserId(),
                tokenResponses.getToken());

        createdUserResponse.getBody().prettyPrint();
    }
}
