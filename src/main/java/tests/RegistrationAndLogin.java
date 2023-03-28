package tests;

import api.GetRequests;
import api.PostRequestRegistration;
import api.PostRequestsLogin;
import helpers.Assertion;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {
    private static String email;
    private static String password;
    private static  String name;
     static String responseCode;
    @BeforeTest
    public static void credentials() {
        name = "Toni1";
        email = "Toni_1213@asd.com";
        password = "k253_@!1Gd";
    }
     @Test
    public static void testSuccessRegistration() throws IOException{
        PostRequestRegistration postRequestRegistration = new PostRequestRegistration();
        PostRequestRegistration.registrationUser(name, email, password);
        responseCode = PostRequestRegistration.getResponseCode();

        Assertion.validAssertion(postRequestRegistration, responseCode);
    }
    @Test
    public static void testSuccessfulLogin() throws IOException {
        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        postRequestsLogin.login(email, password);

        responseCode = postRequestsLogin.getResponseCode();


        Assertion.validAssert(responseCode, postRequestsLogin);
    }
    @Test
    public static void getUser() throws IOException {
        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        GetRequests getUser = new GetRequests();
        postRequestsLogin.login(email, password);
        responseCode = postRequestsLogin.getResponseCode();

        Assertion.validAssert(responseCode, postRequestsLogin );

        getUser.setAccessToken(postRequestsLogin.getAccessToken());
        System.out.println(getUser.getAccessToken());
        getUser.getPageUser();
    }
    @Test
    public static void registAndLoginGetUser() throws IOException {
       String mail = "e12Dgg@hty.com";
       String pass = "7jdjj_2";
        GetRequests getUser = new GetRequests();
        PostRequestRegistration postRequestRegistration = new PostRequestRegistration();
        PostRequestRegistration.registrationUser(name, mail, pass);

        responseCode = PostRequestRegistration.getResponseCode();
        Assertion.validAssertion(postRequestRegistration, responseCode);

        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        postRequestsLogin.login(mail, pass);
        String responseCodeLogin = postRequestsLogin.getResponseCode();
        Assertion.validAssert(responseCodeLogin, postRequestsLogin);

        getUser.setAccessToken(postRequestsLogin.getAccessToken());
        System.out.println(getUser.getAccessToken());
        getUser.getPageUser();
    }
    @Test
    public static void testWrongPassword() throws IOException {
        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        postRequestsLogin.login(email, "123450");
        responseCode = postRequestsLogin.getResponseCode();

        Assertion.invalidAssert(responseCode,postRequestsLogin);
    }
    @Test
    public static void testWrongUsername() throws IOException {
        PostRequestsLogin postRequestsLogin = new PostRequestsLogin();
        postRequestsLogin.login("test@test.com", password);
        responseCode = postRequestsLogin.getResponseCode();

        Assertion.invalidAssert(responseCode,postRequestsLogin);
    }
}
