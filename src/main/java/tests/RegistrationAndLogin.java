package tests;

import api.PostRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {

    private static String email;
    private static String password;

    @BeforeTest
    public static void credentials() {
        email = "zdravko.zafirov+2@gmail.com";
        password = "123456";
    }

    @Test
    public static void testSuccessfulLogin() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(email, password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test
    public static void testWrongPassword() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login(email, "123450");
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void testWrongUsername() throws IOException {
        PostRequests postRequests = new PostRequests();
        postRequests.login("test@test.com", password);
        String responseCode = postRequests.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequests.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

}
