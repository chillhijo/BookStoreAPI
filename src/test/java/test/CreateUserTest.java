package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import requests.CreateUser;
import responses.CreateUserErrorResponse;
import responses.CreateUserResponse;
import util.Constants;
import util.RandomCredentialGenerator;
import util.VerifyStatusCode;
import java.io.IOException;

public class CreateUserTest extends RestApiBase {
    public CreateUserTest() throws IOException {
    }

    @Test (priority = 1, description = "Create new user with random generated username and password")
    public void createNewUserValidTest() {
        RandomCredentialGenerator generator = new RandomCredentialGenerator();
        String newUserName = generator.generateRandomUsername();
        String newPassword = generator.generateRandomPassword();

        AccountRequestBody newUserBody = new AccountRequestBody(
                newUserName, newPassword);

        Response response = CreateUser.createUser(
                bookstore_properties.getValue(Constants.ENDPOINT_USER),
                newUserBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertCreateUserSuccessStatusCode(statusCode);

        Gson gson = new Gson();
        CreateUserResponse createUserResponse = gson.fromJson(response.getBody().asString(), CreateUserResponse.class);
        createUserResponse.assertBookListEmpty();
        createUserResponse.assertUsernameSameAs(newUserName);

    }

    @Test (priority = 2, description = "Try to create new user with already existing user credentials")
    public void userAlreadyExistingTest() {
        AccountRequestBody existingUserBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = CreateUser.createUser(
                bookstore_properties.getValue(Constants.ENDPOINT_USER),
                existingUserBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthUserExistsStatusCode(statusCode);

        Gson gson = new Gson();
        CreateUserErrorResponse userErrorResponse = gson.fromJson(response.getBody().asString(), CreateUserErrorResponse.class);
        userErrorResponse.validateUserEndpoint();
    }

    @Test (priority = 3, description = "Try to create new user with invalid password")
    public void createNewUserEmptyUsernameTest() {
        RandomCredentialGenerator generator = new RandomCredentialGenerator();
        String newUserName = generator.generateRandomUsername();
        String newPassword = "invalidpass";

        AccountRequestBody newUserBody = new AccountRequestBody(
                newUserName, newPassword);

        Response response = CreateUser.createUser(
                bookstore_properties.getValue(Constants.ENDPOINT_USER),
                newUserBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);

        Gson gson = new Gson();
        CreateUserErrorResponse userErrorResponse = gson.fromJson(response.getBody().asString(), CreateUserErrorResponse.class);
        userErrorResponse.validateUserEndpoint();
    }
}
