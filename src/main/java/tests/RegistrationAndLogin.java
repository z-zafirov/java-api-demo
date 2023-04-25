package tests;

import api.PostLogin;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {

    private static String email;
    private static String password;

    @BeforeTest
    public static void credentials() {
        email = "zdravko.zafirov+1@gmail.com";
        password = "123456";
    }

    @Test
    public static void testSuccessfulLogin() throws IOException {
        PostLogin postLogin = new PostLogin();
        postLogin.login(email, password);
        String responseCode = postLogin.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postLogin.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test
    public static void testWrongPassword() throws IOException {
        PostLogin postLogin = new PostLogin();
        postLogin.login(email, "123450");
        String responseCode = postLogin.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postLogin.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void testWrongUsername() throws IOException {
        PostLogin postLogin = new PostLogin();
        postLogin.login("test@test.com", password);
        String responseCode = postLogin.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postLogin.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

}
