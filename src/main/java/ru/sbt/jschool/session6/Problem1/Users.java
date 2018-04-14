package main.java.ru.sbt.jschool.session6.Problem1;


import main.java.ru.sbt.jschool.session6.Problem1.Formatter.JSONFormatter;
import main.java.ru.sbt.jschool.session6.Problem1.Formatter.JSONFormatterImpl;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;


public class Users {

    public static void main(String[] args) {

        Users u = new Users();




    }

    private JSONFormatter formatter = new JSONFormatterImpl();
    private  String path = "database/";

    public void setPath(String path){
        this.path = path;
    }

    public User getById(int id){

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path + id +  ".dat"))){
            User u = (User)ois.readObject();
            return u;

        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public String get(int id){
        return formatter.marshall(getById(id));
    }

    public int save(User users){
        int id = getId();
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path + id +  ".dat"))){

            oos.writeObject(users);

        }
        catch(Exception ex){

            System.err.println(ex.getMessage());
        }
        return id;
    }

    public boolean delete(int id){
        return new File(path + id + ".dat").delete();
    }

    public List<String> getAll(){

        return Arrays.stream(getAllId().toArray()).map(x -> formatter.marshall(getById((int)x)))
                .collect(Collectors.toList());
    }


    public int getId(){

        List<Integer> IDs = getAllId();
        IDs.sort(Integer::compareTo);

        Optional<Integer> n = Arrays.stream(IDs.toArray()).takeWhile(x -> (int)x == IDs.indexOf(x)+1)
                .map(x -> (int)x)
                .max(Integer::compareTo);

        return !n.equals(Optional.empty())? n.get()+1: 1;
    }


    public List<Integer> getAllId() {

         File dir = new File(path);

        return Arrays.stream(Objects.requireNonNull(dir.list((folder1, name) -> name.endsWith(".dat"))))
                .map(name -> Integer.parseInt(name.substring(0, name.length() - 4)))
                .collect(Collectors.toList());
    }

}
