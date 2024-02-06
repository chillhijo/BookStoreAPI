package base;

import io.restassured.RestAssured;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import util.Constants;
import util.EmailSender;
import util.PropertiesUtil;

import java.io.IOException;

public class RestApiBase {

    protected PropertiesUtil bookstore_properties;
    public RestApiBase() throws IOException {
        bookstore_properties = new PropertiesUtil(Constants.PROPERTIES_FILE);
    }
    private static final String API_BASE_URL = "https://demoqa.com/";

    @BeforeTest
    public static void setUp() {
        RestAssured.baseURI = API_BASE_URL;
    }

    @AfterSuite
    public void sendEmailReport() {
        EmailSender emailSender = new EmailSender();
        emailSender.sendEmailReport();
    }
}
