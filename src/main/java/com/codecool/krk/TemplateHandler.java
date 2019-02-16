package com.codecool.krk;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;

import java.io.IOException;
import java.io.OutputStream;

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

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    protected abstract void setupModel(JtwigModel model);
}
