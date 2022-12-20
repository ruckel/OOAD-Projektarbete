package main;

import java.io.FileInputStream;
import java.util.Properties;

public class Property {

    private static Property instance = new Property();

    private Properties p = new Properties();
    private Property() {
        try {
            p.load(new FileInputStream("src/main/settings.properties"));
        }
        catch (Exception e){
            System.out.println("\"settings.properties\" missing");
        }
    }

    public static Property getInstance(){
        return instance;
    }

    public String getRandomString(){return "123";}
    public String getProperty(String input){return p.getProperty(input);}
    public void setProperty(String property, String input){p.setProperty(property, input);}

}
