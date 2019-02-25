package com.codecool.krk.controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public abstract class TemplateHandler implements HttpHandler {

    private String twigString;

    public TemplateHandler(String twigString){
        this.twigString = twigString;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        // get a template file
        JtwigTemplate template = JtwigTemplate.classpathTemplate(twigString);

        // create a model that will be passed to a template
        JtwigModel model = JtwigModel.newModel();
        setupModel(model);

        // render a template to a string
        String response = template.render(model);
        System.out.println(response);

        httpExchange.getResponseHeaders().add("Content-Type", "text/html");
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        os.write(bytes);
        System.out.println("dupa");
        os.close();
    }

    protected abstract void setupModel(JtwigModel model);
}
