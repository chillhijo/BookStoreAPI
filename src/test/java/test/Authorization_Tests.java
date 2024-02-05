package test;

import base.RestApiBase;
import com.google.gson.Gson;
import io.restassured.response.Response;
import org.testng.Assert;
import payloads.AccountRequestBody;
import requests.Authorized;
import org.testng.annotations.Test;
import responses.AuthorizationErrorResponse;
import util.Constants;
import util.VerifyStatusCode;

import java.io.IOException;

public class Authorization_Tests extends RestApiBase {

    public Authorization_Tests() throws IOException {
        super();
    }

    @Test(priority = 1)
    public void validUserAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = Authorized.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthOKStatusCode(statusCode);

        String responseBody = response.getBody().asString();
        Assert.assertEquals(responseBody, "true",
                "Unexpected response body ");
    }

    @Test(priority = 2)
    public void invalidUserCredentialsAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.INVALID_USERNAME),
                bookstore_properties.getValue(Constants.INVALID_PASSWORD));

        Response response = Authorized.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthErrorStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AuthorizationErrorResponse.class);
            responseObj.assertInvalidUserAuthErrorResponse();
        }
    }

    @Test(priority = 3)
    public void invalidUsernameAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.INVALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = Authorized.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthErrorStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AuthorizationErrorResponse.class);
            responseObj.assertInvalidUserAuthErrorResponse();
        }
    }

    @Test(priority = 4)
    public void invalidUserPasswordAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.INVALID_PASSWORD));

        Response response = Authorized.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthErrorStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AuthorizationErrorResponse.class);
            responseObj.assertInvalidUserAuthErrorResponse();
        }
    }

    @Test(priority = 5)
    public void emptyUserPasswordAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.EMPTY_PASSWORD));

        Response response = Authorized.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AuthorizationErrorResponse.class);
            responseObj.validateAuthorization();
        }
    }

    @Test(priority = 6)
    public void emptyUsernameAuthorization_Test() {
        AccountRequestBody accountRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.EMPTY_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = Authorized.authorizedUser(
                bookstore_properties.getValue(Constants.ENDPOINT_AUTH),
                accountRequestBody);

        int statusCode = response.getStatusCode();
        VerifyStatusCode.assertAuthNotFoundStatusCode(statusCode);

        String responseJson = response.getBody().asString();
        if (statusCode != 200) {
            AuthorizationErrorResponse responseObj = new Gson().fromJson(responseJson, AuthorizationErrorResponse.class);
            responseObj.validateAuthorization();
        }
    }
}
