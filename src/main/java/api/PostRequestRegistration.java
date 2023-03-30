package api;

import helpers.ConfigJson;
import helpers.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class PostRequestRegistration {

    private static String registrationUrl = "http://restapi.adequateshop.com/api/authaccount/registration";

    private static String responseCode;
    private static String responseBody;
    private static String accessToken;
    private static String authMessage;


    public static void registrationUser(String name, String email, String password) throws IOException {
        ConfigJson registrationUrl = new ConfigJson();

        String registUrl = registrationUrl.postRegistrationUrl();

        //Build post registration request
        String postBody = "{\"name\":\"" + name + "\", " + "\"email\":\"" + email + "\", " + "\"password\":\"" + password + "\"}";

       HttpPost postRegistration = new HttpPost(registUrl);
        //принт
        System.out.println(postRegistration);
        postRegistration.setEntity(new StringEntity(postBody));

        postRegistration.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = httpClient.execute(postRegistration);

        responseCode = response.getStatusLine().toString();

        HttpEntity entity = response.getEntity();
        if(entity != null){
            // A Simple JSON Response Read
            InputStream instream = entity.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }
        if(responseCode.contains("200") == true){
            JsonParser json = new JsonParser();
            String authCode = json.getResponseCode(responseBody);
            authMessage = json.getAuthMessage(responseBody);
            if (authCode == "0") {
                accessToken = json.getAccessToken(responseBody);
            }
        }
    }
    public static String getAccessToken(){
        return accessToken;
    }
    public static String getResponseCode(){
        return responseCode;
    }
    public static String getResponseBody(){
        return responseBody;
    }
    public static   String getRegistrationMessage(){
        return authMessage;
    }
}
