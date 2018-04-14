package main.java.ru.sbt.jschool.session6.Problem1;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Server {

    private String path = "config.ini";
    private Property property;
    public static void main(String[] args)    {

        String config = null;
        if (args.length>0) {
            config = args[0];

        }

        Server server = new Server(config);
        server.start();

    }
    public Server(String path){
        if(path == null){
            path = this.path;
        }
        property = new Property(path);
    }

    private String getResponse(String request){

        System.out.println(request);
        String[] parse = request.split("[//%&?\\ ]");
        Arrays.stream(parse).forEach(System.out::println);

        Users users = new Users();

        Map<String ,String> args = Arrays.stream(parse)
                .filter(s -> s.contains("="))
                .map(s -> s.split("="))
                .collect(Collectors.toMap(x -> (String) x[0],x -> (String) x[1]));


        if(!parse[2].equals("user")) return null;
        String command = parse[3];
        switch (command){
            case "create": {
                return "userID: " + users.save(new User(args.get("name"),
                        Integer.parseInt(args.get("age")),
                        Integer.parseInt(args.get("salary"))));
            }
            case "delete": {
                return users.delete(Integer.parseInt(parse[4]))? "200 OK" : null;
            }
            case "list": {
                return users.getAll().stream().reduce((acc, str) -> acc + "</p>" + str).get();
            }
            case "$":
                return users.get(Integer.parseInt(parse[4].substring(2, parse[4].length())));
        }


        return null;
    }

    public void start(){
        ServerSocket ss = null;

         try {

             ss = new ServerSocket(property.getPort("PORT"));

         } catch(Exception e) {

         }
         while (true) {
             try {

                 Socket socket = Objects.requireNonNull(ss).accept();

                 InputStream input = socket.getInputStream();
                 OutputStream output = socket.getOutputStream();

                 Scanner scanner = new Scanner(input);
                 PrintWriter writer = new PrintWriter(output);

                 String request = scanner.nextLine();
                 if (request.equals("favicon.ico")) {
                     continue;
                 }
                 String response = getResponse(request);

                 if (response != null) {
                     writer.println(State.OK);
                     writer.println(response);
                     writer.flush();
                 } else {
                     writer.println(State.NOT_FOUND);
                     writer.flush();
                 }
             } catch (Exception e) {
                 System.err.println(e.getMessage());
             }

         }


    }
}
