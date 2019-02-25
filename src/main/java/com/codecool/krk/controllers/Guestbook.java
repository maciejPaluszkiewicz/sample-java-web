package com.codecool.krk.controllers;

import com.codecool.krk.DAO.MessageDAO;
import com.codecool.krk.DAO.MessageDAOfromPSQL;
import com.codecool.krk.helpers.CookieHelper;
import com.codecool.krk.helpers.FormHelper;
import com.codecool.krk.model.Message;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class Guestbook extends TemplateHandler {

    private String username;
    private MessageDAO messageDAO = new MessageDAOfromPSQL();

    public Guestbook() {
        super("templates/guestbook.twig");
    }

    @Override
    protected void setupModel(JtwigModel model) {
        model.with("username", username);
        try {
            model.with("messages", messageDAO.getAllMessages());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Optional<HttpCookie> currentCookie = CookieHelper.getCookie(httpExchange, CookieHelper.COOKIE_USERNAME);
        if (currentCookie.isPresent()) {
            username = currentCookie.get().getValue().replaceAll("\"", "");
        } else {
            username = "Guest";
        }

        if (httpExchange.getRequestMethod().equals("GET")) {
            super.handle(httpExchange);
        } else {
            try {
                String formData = FormHelper.formData(httpExchange);
                Map<String, String> inputs = FormHelper.parseFormData(formData);
                Message message = new Message(username, new Date(), inputs.get("textmessage"));
                messageDAO.addNewMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            httpExchange.getResponseHeaders().set("Location", "/guestbook");
            httpExchange.sendResponseHeaders(302, 0);
        }
    }
}
