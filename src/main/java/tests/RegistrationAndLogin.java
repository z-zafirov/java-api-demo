package tests;

import api.PostRequests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationAndLogin {

    private static String name;
    private static String salary;
    private static Integer age;

    @BeforeTest
    public static void credentials() {
        String name = "zdravko zafirov";
        String salary = "123,45";
        Integer age = 23;
    }

    @Test
    public static void testSuccessfulCreate() throws IOException {
        PostRequests postCreate = new PostRequests();
        postCreate.createRequest(name, salary, age);
        String responseCode = postCreate.getResponseCode();
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String responseBody = postCreate.getResponseBody();
        Assert.assertTrue(responseBody.contains("success"), responseBody);
        System.out.println(responseBody);
    }
/*
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
*/
}
