package com.codecool.krk.controllers;

import com.codecool.krk.helpers.CookieHelper;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import java.io.IOException;

public class Index extends TemplateHandler {

    public Index() {
        super("templates/index.twig");
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        CookieHelper.deleteCookie(httpExchange, CookieHelper.COOKIE_USERNAME);
        super.handle(httpExchange);
    }

    @Override
    protected void setupModel(JtwigModel model) {
    }
}