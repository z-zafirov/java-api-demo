package helpers;
public class ConfigJson {
    private static String baseUrl = getData(1);;
    private static String home;
    private static String protocol  = getData(0);;
    private static String protocolPart = "://";



   // private static String FancyData = " {\n" +
   //         "    \"url\": \"restapi.adequateshop.com/api\",\n" +
   //         "    \"email\": \"asdkjhsdk@asdjh.som\",\n" +
   //         "    \"name\": \"Zohkjhri\",\n" +
   //         "    \"password\": \"lsdkjlsdkj@#$\",\n" +
   //         "}";
    public static String postRegistrationUrl() {

        home = String.format(protocol + protocolPart + baseUrl + "/AuthAccount/Registration");
        System.out.println(home);
        return home;
    }
    public static String postLoginRequestUrl() {

        home  =  String.format(protocol + protocolPart + baseUrl + "/AuthAccount/Login");
        System.out.println(home);
        return home;
    }
    public static String getRequestUrl() {

        home = String.format(protocol + protocolPart + baseUrl + "/users?page=1");
        System.out.println(home);
        return home;
    }
    public static String getData(int number) {
        //Out of bounds for length to 34 line
        ReadConfig reader = new ReadConfig();
        String[] json = (reader.reader()).split(",");
        String data = json[number];
        data = data.replace("{", "");
        String[] key = data.split(":");
        String value = key[1];
        String message = value.replace("\"", "");
        return message.trim();
    }
}
