package com.codecool.krk.controllers;

import com.codecool.krk.DAO.LoginDAO;
import com.codecool.krk.DAO.LoginDAOfromPSQL;
import com.codecool.krk.helpers.CookieHelper;
import com.codecool.krk.helpers.FormHelper;
import com.codecool.krk.helpers.HashHelper;
import com.codecool.krk.helpers.SessionHelper;
import com.sun.net.httpserver.HttpExchange;
import org.jtwig.JtwigModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            super.handle(httpExchange);
        } else {
            InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String formData = br.readLine();

            System.out.println(formData);
            Map <String, String> inputs = FormHelper.parseFormData(formData);

            // TODO: 19.02.19 check if all user/passw is accurate, save session -
            //  connection with DAO, then redirect to questbook if correct,
            //  else say info about wrong data for logging in.
            //  nadpisac errorMsg i wykonac superhandlehttpexchange;

            try {
                String hassh = HashHelper.createHash(inputs.get("password"));
                boolean verification = loginDAO.checkIfLoginAndPasswordAreCorrect(
                        inputs.get("login"),
                        hassh);
                if(verification){
                    //TODO add to session username
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
