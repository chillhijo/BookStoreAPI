package responses;

import org.testng.Assert;

public class CreateUserErrorResponse {

    private int code;
    private String message;

    public CreateUserErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public void validateUserEndpoint() {
        if (code == 1300) {
            assertWrongPasswordUser();
            System.out.println("Wrong password");
        } else if (code == 1204) {
            assertAlreadyExistingUser();
            System.out.println("Existing user");
        }else {
            System.out.println("Unhandled authorization error code: " + code);
        }
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

    public void assertAlreadyExistingUser() {
        Assert.assertEquals(code,
                1204,
                "Incorrect code");

        Assert.assertEquals(message,
                "User exists!",
                "User doesn`t exist in base!");
    }
}
