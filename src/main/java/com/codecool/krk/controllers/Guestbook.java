package com.codecool.krk.controllers;

import org.jtwig.JtwigModel;

public class Guestbook extends TemplateHandler {


    public Guestbook() {
        super("templates/guestbook.twig");
    }

    @Override
    protected void setupModel(JtwigModel model) {

    }
}
