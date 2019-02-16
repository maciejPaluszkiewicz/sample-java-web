package com.codecool.krk;

import org.jtwig.JtwigModel;

public class Login extends TemplateHandler {


    public Login() {
        super("templates/login.twig");
    }

    @Override
    protected void setupModel(JtwigModel model) {

    }
}
