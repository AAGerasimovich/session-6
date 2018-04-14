package main.java.ru.sbt.jschool.session6.Problem1;

public enum State {
    OK{
        @Override
        public String toString() {
            return  "HTTP/1.1 200 OK\n" +
                    "Content-Type: text/html\r\n\n";
        }
    },
    NOT_FOUND{
        @Override
        public String toString() {
            return "HTTP/1.1 404 Not Found\n" +
                    "Content-Type: text/html\r\n\n"+
                    "404 NOT FOUND";
        }
    }
}
