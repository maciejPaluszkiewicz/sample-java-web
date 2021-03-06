package com.codecool.krk;

import com.codecool.krk.controllers.Guestbook;
import com.codecool.krk.controllers.Index;
import com.codecool.krk.controllers.Login;
import com.codecool.krk.controllers.Static;
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {
        // create a server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // set routes
        server.createContext("/static", new Static());
        server.createContext("/index", new Index());
        server.createContext("/login", new Login());
        server.createContext("/guestbook", new Guestbook());
        server.setExecutor(null); // creates a default executor

        // start listening
        server.start();
        System.out.println("zomg server started!");
    }
}
