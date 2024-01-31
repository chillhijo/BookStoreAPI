package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import requests.AuthorizedRequest;
import responses.AuthorizationErrorResponse;
import responses.GenerateTokenResponses;
import util.Constants;
import util.VerifyStatusCode;

import java.io.IOException;

public class GenerateTokenTest extends RestApiBase {
    public GenerateTokenTest() throws IOException {
    }

    @Test
    public void generateTokenValidCredentials() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = AuthorizedRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_GENERATE_TOKEN),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthOKStatusCode(statusCode);

        Gson gson = new Gson();
        GenerateTokenResponses generateTokenResponses = gson.fromJson(response.getBody().asString(), GenerateTokenResponses.class);
        generateTokenResponses.validateTokenResult();
    }

    @Test
    public void generateTokenInvalidCredentials() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.INVALID_USERNAME),
                bookstore_properties.getValue(Constants.INVALID_PASSWORD));

        Response response = AuthorizedRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_GENERATE_TOKEN),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthOKStatusCode(statusCode);

        Gson gson = new Gson();
        GenerateTokenResponses generateTokenResponses = gson.fromJson(response.getBody().asString(), GenerateTokenResponses.class);
        generateTokenResponses.validateTokenResult();
    }

    @Test
    public void generateTokenInvalidEndpoint() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = AuthorizedRequest.authorizedUser(
                bookstore_properties.getValue(Constants.INVALID_ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthErrorStatusCode(statusCode);
    }

    @Test
    public void generateTokenEmptyUserName() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.EMPTY_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = AuthorizedRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_GENERATE_TOKEN),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);

        Gson gson = new Gson();
        AuthorizationErrorResponse authorizationErrorResponse = gson.fromJson(response.getBody().asString(), AuthorizationErrorResponse.class);
        authorizationErrorResponse.validateAuthorization();
    }

    @Test
    public void generateTokenEmptyPassword() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.EMPTY_PASSWORD));

        Response response = AuthorizedRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_GENERATE_TOKEN),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);

        Gson gson = new Gson();
        AuthorizationErrorResponse authorizationErrorResponse = gson.fromJson(response.getBody().asString(), AuthorizationErrorResponse.class);
        authorizationErrorResponse.validateAuthorization();
    }
}
