package helpers;
public class ConfigJson {
    private static String baseUrl;
    private static String home;
     private static String protocol = "http://";
    private static String FancyData = " {\n" +
            "    \"url\": \"restapi.adequateshop.com/api\",\n" +
            "    \"email\": \"asdkjhsdk@asdjh.som\",\n" +
            "    \"name\": \"Zohkjhri\",\n" +
            "    \"password\": \"lsdkjlsdkj@#$\",\n" +
            "}";
    public static String postRegistrationUrl() {
        baseUrl = getData(0);
        home = String.format(protocol + baseUrl + "/AuthAccount/Registration");
        System.out.println(home);
        return home;
    }
    public static String postLoginRequestUrl() {
        baseUrl = getData(0);
        home  =  String.format(protocol + baseUrl + "/AuthAccount/Login");
        System.out.println(home);
        return home;
    }
    public static String getRequestUrl() {
        baseUrl = getData(0);
        home = String.format(protocol + baseUrl + "/users?page=1");
        System.out.println(home);
        return home;
    }
    public static String getData( int number) {
        String[] json = FancyData.split(",");
        String data = json[number];
        data = data.replace("{", "");
        String[] key = data.split(":");
        String value = key[1];
        String message = value.replace("\"", "");
        return message.trim();
    }
}
