package test;

import base.RestApiBase;
import io.restassured.response.Response;
import net.bytebuddy.matcher.CollectionOneToOneMatcher;
import org.testng.annotations.Test;
import payloads.AccountRequestBody;
import requests.LoginUser;
import util.Constants;

import java.io.IOException;

public class LoginUserTest extends RestApiBase {
    public LoginUserTest() throws IOException {
    }

    @Test
    public void loginUserTest() {
        AccountRequestBody loginRequestBody = new AccountRequestBody(
                bookstore_properties.getValue(Constants.VALID_USERNAME),
                bookstore_properties.getValue(Constants.VALID_PASSWORD));

        Response response = LoginUser.loginUser(
                bookstore_properties.getValue(Constants.ENDPOINT_LOGIN_USER),
                loginRequestBody);

        response.getBody().prettyPrint();
    }
}
