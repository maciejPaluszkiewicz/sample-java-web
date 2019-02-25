package com.codecool.krk.helpers;

import com.sun.net.httpserver.HttpExchange;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CookieHelper {

    public static String COOKIE_USERNAME = "username";

    public static void setCookie(HttpExchange httpExchange, String cookieName, String value){
        Optional<HttpCookie> cookie = Optional.of(new HttpCookie(cookieName, value));
        httpExchange.getResponseHeaders().add("Set-Cookie", cookie.get().toString());
    }

    public static void deleteCookie(HttpExchange httpExchange, String cookieName){
        Optional<HttpCookie> cookie = Optional.of(new HttpCookie(cookieName, ""));
        httpExchange.getResponseHeaders().add("Set-Cookie",
                cookie.get().toString() + "; expires=Thu, 01 Jan 1970 00:00:00 GMT");
    }

    public static Optional<HttpCookie> getCookie(HttpExchange httpExchange, String cookieName){
        String cookieStr = httpExchange.getRequestHeaders().getFirst("Cookie");
        List<HttpCookie> cookies = parseCookies(cookieStr);
        return findCookieByName(cookieName, cookies);
    }

    private static List<HttpCookie> parseCookies(String cookieString){
        List<HttpCookie> cookies = new ArrayList<>();
        if(cookieString == null || cookieString.isEmpty()){ // what happens if cookieString = null?
            return cookies;
        }
        for(String cookie : cookieString.split(";")){
            int indexOfEq = cookie.indexOf('=');
            String cookieName = cookie.substring(0, indexOfEq);
            String cookieValue = cookie.substring(indexOfEq + 1);
            cookies.add(new HttpCookie(cookieName, cookieValue));
        }
        return cookies;
    }

    private static Optional<HttpCookie> findCookieByName(String name, List<HttpCookie> cookies){
        for(HttpCookie cookie : cookies){
            if(cookie.getName().equals(name))
                return Optional.ofNullable(cookie);
        }
        return Optional.empty();
    }
}
