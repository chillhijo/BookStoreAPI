package util;

import org.testng.Assert;

public class VerifyStatusCode {
    public static void assertAuthOKStatusCode(int statusCode) {
        Assert.assertEquals(200, statusCode, "Unexpected status code");
        System.out.println("Status code is: " + statusCode);
    }

    public static void assertCreateUserSuccessStatusCode(int statusCode) {
        Assert.assertEquals(201, statusCode, "Unexpected status code");
        System.out.println("Status code is: " + statusCode);
    }

    public static void assertAuthErrorStatusCode(int statusCode) {
        Assert.assertEquals(404, statusCode, "Unexpected status code");
        System.out.println("Status code is: " + statusCode);
    }

    public static void assertAuthNotFoundStatusCode(int statusCode) {
        Assert.assertEquals(400, statusCode, "Unexpected status code");
        System.out.println("Status code is: " + statusCode);
    }

    public static void assertAuthUserExistsStatusCode(int statusCode) {
        Assert.assertEquals(406, statusCode, "Unexpected status code");
        System.out.println("Status code is: " + statusCode);
    }
}
