package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import requests.CreateUserRequest;
import requests.GenerateToken;
import responses.CreateUserErrorResponse;
import util.Constants;
import util.RandomCredentialGenerator;
import util.VerifyStatusCode;

import java.io.IOException;
import java.lang.reflect.GenericSignatureFormatError;

public class CreateUserTest extends RestApiBase {
    public CreateUserTest() throws IOException {
    }

    @Test
    public void createNewUserValidTest() {
        RandomCredentialGenerator generator = new RandomCredentialGenerator();
        String newUserName = generator.generateRandomUsername();
        String newPassword = generator.generateRandomPassword();

        AccountRequestBody newUserBody = new AccountRequestBody(
                newUserName, newPassword);

        Response response = CreateUserRequest.createUser(
                bookstore_properties.getValue(Constants.ENDPOINT_CREATE_USER),
                newUserBody);
        response.getBody().prettyPrint();

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertCreateUserSuccessStatusCode(statusCode);
    }

    @Test
    public void createNewUserEmptyUsernameTest() {
        RandomCredentialGenerator generator = new RandomCredentialGenerator();
        String newUserName = generator.generateRandomUsername();
        String newPassword = "invalidpass";

        AccountRequestBody newUserBody = new AccountRequestBody(
                newUserName, newPassword);

        Response response = CreateUserRequest.createUser(
                bookstore_properties.getValue(Constants.ENDPOINT_CREATE_USER),
                newUserBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);


        Gson gson = new Gson();
        CreateUserErrorResponse userErrorResponse = gson.fromJson(response.getBody().asString(), CreateUserErrorResponse.class);
        userErrorResponse.assertWrongPasswordUser();
    }
}
