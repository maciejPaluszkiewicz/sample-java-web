package com.codecool.krk.controllers;

import com.codecool.krk.helpers.CookieHelper;
import com.codecool.krk.model.Message;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;

import java.io.IOException;
import java.net.HttpCookie;
import java.util.Optional;

public class Guestbook extends TemplateHandler {

    private String username;

    public Guestbook() {
        super("templates/guestbook.twig");
    }

    @Override
    protected void setupModel(JtwigModel model) {
        model.with("username", username);
        //model.with("messages", // TODO: list messages (from DAO));
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Optional<HttpCookie> currentCookie = CookieHelper.getCookie(httpExchange, CookieHelper.COOKIE_USERNAME);
        if(currentCookie.isPresent()){
            username = currentCookie.get().getValue().replaceAll("\"", "");
        } else {
            username = "Guest";
        }


        if (httpExchange.getRequestMethod().equals("GET")) {
            super.handle(httpExchange);
        } else {
            //TODO: get form data, add Message (from DAO)


            // at the end:
            //httpExchange.getResponseHeaders().set("Location", "/guestbook");
            //httpExchange.sendResponseHeaders(302,0);
        }
    }
}
