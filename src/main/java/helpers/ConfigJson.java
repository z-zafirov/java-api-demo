package helpers;

import org.json.JSONObject;

import java.net.*;

public class ConfigJson {
    private static URL base;
    private static URL home;

  //  public static void baseUrl(){
  //      String partUr = "registration";
  //      JSONObject url = new JSONObject();
  //      url.put("url", "http://restapi.adequateshop.com/api/authaccount/");
  //      String completeUrl =  url + partUr;
  //  }
    public static void RegistrationData(){
        JSONObject name =  new JSONObject();
        name.put("name","Petq");
        JSONObject email = new JSONObject();
        email.put("email", "Petq_123rt@vhd.com");
        JSONObject password = new JSONObject();
        password.put("password", "!eddfffdf12_R");
    }
    public static void getUrl() throws MalformedURLException {
        base = new URL("http://restapi.adequateshop.com/api/authaccount");
    }
    public static void postRegistrationUrl() throws MalformedURLException {
        home = new URL(base, "/registration");
    }
    public static void postLoginRequestUrl() throws MalformedURLException {
        home  = new URL(base,"/login");
    }
    public static void getRequestUrl() throws MalformedURLException {
        home = new URL(base, "/users?page=1");
    }

}
