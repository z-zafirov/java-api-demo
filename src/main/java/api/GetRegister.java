package api;

import java.net.*;
import java.util.List;

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
            // make sure to set CookiePolicy
            CookieManager manager = new CookieManager();
            manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            CookieHandler.setDefault(manager);

            // get content from URLConnection;
            // cookies are set by web site
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.getContent();

            // get cookies from underlying
            // CookieStore
            CookieStore cookieJar =  manager.getCookieStore();
            List<HttpCookie> cookies =
                    cookieJar.getCookies();
            for (HttpCookie cookie: cookies) {
                // It is only one cookie value that matters, but in case of many - loop through them:
                responseCookie = String.valueOf(cookie);
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

