package responses;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;

@Getter
@Setter
public class GenerateTokenResponses {
    private String token;
    private String expires;
    private String status;
    private String result;

    public GenerateTokenResponses(String token, String expires, String status, String result) {
        this.token = token;
        this.expires = expires;
        this.status = status;
        this.result = result;
    }

    public String validateTokenResult() {
        if (token != null) {
            assertTokenResult();
            assertTokenStatus();
        }
        else {
            System.out.println("User authorization failed.");
        }
        System.out.println("token: "+token);
        return token;
    }

    public void assertTokenStatus() {
        Assert.assertEquals(status,
                "Success",
                "Incorrect status");
    }

    public void assertTokenResult() {
        Assert.assertEquals(result,
                "User authorized successfully.",
                "User is not authorized!");
    }
}
