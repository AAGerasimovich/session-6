package main.java.ru.sbt.jschool.session6.Problem1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Property {

    private static Properties property;

    public Property(String path){

        property = new Properties();
        try {
            property.load(new FileInputStream(path));
        }catch (FileNotFoundException e){
            System.err.println("File config.ini not found");
        }catch (IOException e){
            System.err.println(e);
        }
    }
    public int getPort(String name){
        return Integer.parseInt(property.getProperty(name));

    }



}
