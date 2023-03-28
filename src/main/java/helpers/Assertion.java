package helpers;

import api.PostRequestRegistration;
import api.PostRequestsLogin;
import org.testng.Assert;

public class Assertion {
    public static void validAssertion(PostRequestRegistration postRequestRegistration, String responseCode){
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequestRegistration.getRegistrationMessage();
        Assert.assertTrue(authMessage.contains("success"), authMessage);
    }
    public static void invalidAssert (String responseCode, PostRequestsLogin postRequestsLogin){
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessage = postRequestsLogin.getLoginMessage();
        Assert.assertTrue(authMessage.contains("invalid"), authMessage);
    }
    public static void validAssert(String responseCode,PostRequestsLogin postRequestsLogin){
        Assert.assertTrue(responseCode.contains("200"), responseCode);
        String authMessageLogin = postRequestsLogin.getLoginMessage();
        Assert.assertTrue(authMessageLogin.contains("success"), authMessageLogin);
    }
}
