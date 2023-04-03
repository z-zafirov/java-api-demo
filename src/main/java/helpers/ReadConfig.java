package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadConfig {
    public static String reader(){
        StringBuilder data = new StringBuilder();
        try {
            String sysPath = System.getProperty("user.dir");
            File myObj = new File(sysPath + "\\src\\main\\java\\Config\\config.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
                System.out.println(data);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return data.toString();
    }
}
