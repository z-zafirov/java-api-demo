package api;

import java.net.*;
import java.util.List;

/*
* Info about the implementation taken from:
* https://docs.oracle.com/javase/tutorial/deployment/doingMoreWithRIA/accessingCookies.html
*/


public class GetRegister {
    private static String urlString = "https://parabank.parasoft.com/parabank/register.htm";
    private static String responseCode;
    private static String responseCookie;

    public static void main(String[] args) {
        setCookie();
        printCookie();
    }

    public static void setCookie() {
        try {
            // Instantiate CookieManager;
            CookieManager manager = new CookieManager();
            CookieHandler.setDefault(manager);

            // get content from URLConnection;
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.getContent();

            // get cookies from underlying CookieStore
            CookieStore cookieJar =  manager.getCookieStore();
            List<HttpCookie> cookies = cookieJar.getCookies();

            // loop through the cookies and pick the JSESSIONID one
            for (HttpCookie cookie: cookies) {
                if (String.valueOf(cookie).contains("JSESSIONID")) {
                    responseCookie = String.valueOf(cookie);
                }
            }
        } catch(Exception e) {
            System.out.println("Unable to get cookie using CookieHandler");
            e.printStackTrace();
        }
    }

    public static void printCookie() {
        System.out.println("CookieHandler retrieved cookie: " + responseCookie);
    }

}

