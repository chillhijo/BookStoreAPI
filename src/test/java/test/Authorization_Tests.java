package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import payloads.AccountRequestBody;
import requests.AccountRequest;
import org.testng.annotations.Test;
import responses.AccountAuthorizationErrorResponse;
import util.Constants;
import util.VerifyStatusCode;

import java.io.IOException;

public class Authorization_Tests extends RestApiBase {

    public Authorization_Tests() throws IOException {
        super();
    }

    @Test
    public void validUserAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = AccountRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthOKStatusCode(statusCode);

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody, "true",
                "Unexpected response body ");
    }

    @Test
    public void invalidUserCredentialsAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.INVALID_USERNAME),
                bookstore_properties.getValue(Constants.INVALID_PASSWORD));

        Response response = AccountRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthErrorStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AccountAuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AccountAuthorizationErrorResponse.class);
            responseObj.assertInvalidUserAuthErrorResponse();
        }
    }

    @Test
    public void invalidUsernameAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.INVALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = AccountRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthErrorStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AccountAuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AccountAuthorizationErrorResponse.class);
            responseObj.assertInvalidUserAuthErrorResponse();
        }
    }

    @Test
    public void invalidUserPasswordAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.INVALID_PASSWORD));

        Response response = AccountRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthErrorStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AccountAuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AccountAuthorizationErrorResponse.class);
            responseObj.assertInvalidUserAuthErrorResponse();
        }
    }

    @Test
    public void emptyUserPasswordAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.EMPTY_PASSWORD));

        Response response = AccountRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AccountAuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AccountAuthorizationErrorResponse.class);
            responseObj.assertEmptyCredentialAuthErrorResponse();
        }
    }

    @Test
    public void emptyUsernameAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.EMPTY_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = AccountRequest.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AccountAuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AccountAuthorizationErrorResponse.class);
            responseObj.assertEmptyCredentialAuthErrorResponse();
        }
    }
}
