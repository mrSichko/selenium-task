import entity.Comment;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MyUtils {

    public static String getProperty(String key) {
        String value = "";
        try (InputStream input = new FileInputStream("src/main/resources/app.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            value = prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }

    public static String getCodeFromSMS() {
        String code = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                code = reader.readLine();

                if (code.length() == 5) {
                    break;
                }
                System.out.println("Incorrect code, it must contains 5 digits");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static Comment getRandomComment(List<Comment> list){
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }

}
