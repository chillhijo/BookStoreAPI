package responses;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;

@Getter
@Setter
public class AccountAuthorizationErrorResponse {

    private String message;
    private int code;

    public AccountAuthorizationErrorResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public void assertAuthErrorResponse() {
        Assert.assertEquals(code,
                1207,
                "Incorrect code");

        Assert.assertEquals(message,
                "User not found!",
                "Incorrect error description");
    }
}
