package responses;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;

@Getter
@Setter
public class AuthorizationErrorResponse {

    private String message;
    private int code;

    public AuthorizationErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public void validateAuthorization() {
        if (code == 1207) {
            assertInvalidUserAuthErrorResponse();
        } else if (code == 1200) {
            assertEmptyCredentialAuthErrorResponse();
        }else {
            System.out.println("Unhandled authorization error code: " + code);
        }
    }

    public void assertInvalidUserAuthErrorResponse() {
        Assert.assertEquals(code,
                1207,
                "Incorrect code");

        Assert.assertEquals(message,
                "User not found!",
                "Incorrect error description");
        System.out.println("User not found!");
    }

    public void assertEmptyCredentialAuthErrorResponse() {
        Assert.assertEquals(code,
                1200,
                "Incorrect code");

        Assert.assertEquals(message,
                "UserName and Password required.",
                "Incorrect error description");

        System.out.println("UserName and Password required.");
    }

    public void assertAlreadyExistingUser() {
        Assert.assertEquals(code,
                "1204",
                "Incorrect code");

        Assert.assertEquals(message,
                "User exists!",
                "User doesn`t exist in base!");

        System.out.println("User already exists in base!");
    }

}
