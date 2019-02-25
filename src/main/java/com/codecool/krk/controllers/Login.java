package com.codecool.krk.controllers;

import com.codecool.krk.DAO.LoginDAO;
import com.codecool.krk.DAO.LoginDAOfromPSQL;
import com.codecool.krk.helpers.CookieHelper;
import com.codecool.krk.helpers.FormHelper;
import com.codecool.krk.helpers.HashHelper;
import com.codecool.krk.helpers.SessionHelper;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;
import java.io.IOException;
import java.util.Map;

public class Login extends TemplateHandler {

    private String errorMsg;
    private LoginDAO loginDAO = new LoginDAOfromPSQL();

    public Login() {
        super("templates/login.twig");
    }

    @Override
    protected void setupModel(JtwigModel model) {
        model.with("Error", errorMsg);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        SessionHelper.manageSession(httpExchange);

        errorMsg = null;
        if(httpExchange.getRequestMethod().equals("GET")){
            CookieHelper.deleteCookie(httpExchange, CookieHelper.COOKIE_USERNAME);
            super.handle(httpExchange);
        } else {
            try {
                String formData = FormHelper.formData(httpExchange);
                Map <String, String> inputs = FormHelper.parseFormData(formData);
                String hassh = HashHelper.createHash(inputs.get("password"));
                boolean verification = loginDAO.checkIfLoginAndPasswordAreCorrect(
                        inputs.get("login"),
                        hassh);
                if(verification){
                    CookieHelper.setCookie(httpExchange, CookieHelper.COOKIE_USERNAME, inputs.get("login"));
                    httpExchange.getResponseHeaders().set("Location", "/guestbook");
                    httpExchange.sendResponseHeaders(302,0);
                } else {
                    errorMsg = "Login and/or password incorrect. Please try again.";
                    super.handle(httpExchange);
                    return;
                }
            } catch (Exception e){
                e.printStackTrace();
                errorMsg = "Sorry, database error.";
                super.handle(httpExchange);
                return;
            }
        }
    }
}
