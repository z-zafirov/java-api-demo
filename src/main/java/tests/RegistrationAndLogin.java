package tests;

import api.GetRequests;
import api.PostRequestRegistration;
import api.PostRequestsLogin;
import helpers.Assertion;
import helpers.ConfigJson;
import helpers.JsonParser;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {
    private static ConfigJson getDate = new ConfigJson();
    private static String email;
    private static String password;
    private static  String name;
     static String responseCode;
     private static ConfigJson getData = new ConfigJson();

  //  static String FancyData = " {\n" +
  //          "    \"url\": \"http://restapi.adequateshop.com/api\",\n" +
  //          "    \"email\": \"asdkjhsdk@asdjh.som\",\n" +
  //          "    \"name\": \"Zohkjhri\",\n" +
  //          "    \"password\": \"lsdkjlsdkj@#$\",\n" +
  //          "}";
    @BeforeTest
    public static void credentials() {
        email = getDate.getData(1);
        name = getDate.getData(2);
        password = getDate.getData(3);
    }

    public static String getAccessToken(String in) {
        String[] json = in.split(",");
        System.out.println(in);
        String data = json[3]; // see me
        System.out.println(data);
        data = data.replace("}", "");
        System.out.println(data);
        String[] key = data.split(":");
        System.out.println(key);
        String value = key[1];
        System.out.println(value);
        String accessToken = value.replace("\"", "");
        System.out.println(accessToken);
        return accessToken;
    }


    @Test
    public static void TesssT() {
        String something;
        ConfigJson getData = new ConfigJson();
        something = getData.getData( 0);
        System.out.println(something);  // if success should show /Users
        for(int i = 0; i <= 3; i++){
            System.out.println(getData.getData( i));
        }
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
       String mail = "e1jd7rd@hty.com";
       String pass = "7jdjj_1a2";
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
