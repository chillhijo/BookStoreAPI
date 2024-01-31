package responses;

import org.testng.Assert;

public class CreateUserErrorResponse {

    private int code;
    private String message;

    public CreateUserErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void assertWrongPasswordUser() {
        Assert.assertEquals(code,
                1300,
                "Code is valid!");

        Assert.assertEquals(message,
                "Passwords must have at least one non alphanumeric character," +
                        " one digit ('0'-'9'), one uppercase ('A'-'Z')," +
                        " one lowercase ('a'-'z')," +
                        " one special character and Password must be eight characters or longer.",
                "Password is valid");
    }
}
