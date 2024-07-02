package api;

import java.io.IOException;
import java.io.InputStream;

import helpers.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class PostRequests {

    private static String baseUrl = "https://dummy.restapiexample.com/api/v1";
    private static String createUrl = "/create";
    private static String responseCode;
    private static String responseBody;


    public static void main(String[] args) {
        String name = "zdravko zafirov";
        String salary = "123,45";
        Integer age = 23;
        try {
            createRequest(name, salary, age);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        printResponseBody();
    }

    public static void createRequest(String name, String salary, Integer age) throws IOException {
        // Build the post body (json)
        String jName = "\"name\": \"" + name + "\"";
        //System.out.println("JSON name: " + jName);
        String jSalary = "\"salary\": \"" + salary + "\"";
        //System.out.println("JSON salary: " + jSalary);
        String jAge = "\"age\": " + age;
        //System.out.println("JSON age: " + jAge);
        String postBody = "{" + jName + "," + jSalary + "," + jAge + "}";
        //System.out.println("Post body string: " + postBody);
        // Build the request itself (headers+body)
        HttpPost postLogin = new HttpPost(baseUrl+createUrl);
        postLogin.setEntity(new StringEntity(postBody));
        postLogin.setHeader("Content-type", "application/json");
        HttpClient httpClient = HttpClientBuilder.create().build();
        // Execute the post request
        HttpResponse response = httpClient.execute(postLogin);
        // Read the response code
        responseCode = response.getStatusLine().toString();
        // Fill in the response body
        HttpEntity responseObject = response.getEntity();
        if (responseObject != null) {
            // A Simple JSON Response Read
            InputStream instream = responseObject.getContent();
            responseBody = new ResponseReader().convertStreamToString(instream);
            instream.close();
        }

    }



    public static String getResponseCode() {
        return responseCode;
    }

    public static String getResponseBody() {
        return responseBody;
    }


    public static void printResponseBody() {
        System.out.println(responseBody);
    }

}
