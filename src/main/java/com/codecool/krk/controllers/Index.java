package com.codecool.krk.controllers;

import org.jtwig.JtwigModel;

public class Index extends TemplateHandler {


    public Index() {
        super("templates/index.twig");
    }

    @Override
    protected void setupModel(JtwigModel model) {

    }
}