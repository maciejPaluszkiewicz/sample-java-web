package com.codecool.krk.helpers;

import com.sun.net.httpserver.HttpExchange;

import java.net.HttpCookie;
import java.util.Optional;

public class SessionHelper {

    private static String SESSION_COOKIE_NAME = "sessionId";
    private static long SESSION_ID;

    public static void manageSession(HttpExchange httpExchange){
        Optional<HttpCookie> cookie = CookieHelper.getCookie(httpExchange, SESSION_COOKIE_NAME);
        if (!cookie.isPresent()) {
            SESSION_ID = System.currentTimeMillis();
            CookieHelper.setCookie(httpExchange, SESSION_COOKIE_NAME, Long.toString(SESSION_ID));
        }
    }
}
