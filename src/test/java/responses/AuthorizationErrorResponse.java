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

    public void assertInvalidUserAuthErrorResponse() {
        Assert.assertEquals(code,
                1207,
                "Incorrect code");

        Assert.assertEquals(message,
                "User not found!",
                "Incorrect error description");
    }

    public void assertEmptyCredentialAuthErrorResponse() {
        Assert.assertEquals(code,
                1200,
                "Incorrect code");

        Assert.assertEquals(message,
                "UserName and Password required.",
                "Incorrect error description");
    }
}
