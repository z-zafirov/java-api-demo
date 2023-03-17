package tests;

import api.PostRequestRegistration;
import api.PostRequestsLogin;
import org.apache.http.client.HttpClient;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {

    private static String email;
    private static String password;
    private static  String name;

    @BeforeTest
    public static void credentials() {
        name = "Zori";
        email = "petaryy.petrovski@abv.bg";
        password = "123456@!@";
    }
    @Test
    public static void testSuccessRegistration() throws IOException{
        PostRequestRegistration postRequestRegistration = new PostRequestRegistration();
        PostRequestRegistration.registrationUser(name, email, password);
        String responseCode = PostRequestRegistration.getResponseCode();

        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequestRegistration.getRegistrationMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }
    @Test
    public static void testSuccessfulLogin() throws IOException {
        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        postRequestsLogin.login(email, password);
        String responseCode = postRequestsLogin.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequestsLogin.getLoginMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }

    @Test
    public static void testWrongPassword() throws IOException {
        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        postRequestsLogin.login(email, "123450");
        String responseCode = postRequestsLogin.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequestsLogin.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }

    @Test
    public static void testWrongUsername() throws IOException {
        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        postRequestsLogin.login("test@test.com", password);
        String responseCode = postRequestsLogin.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequestsLogin.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }


}
