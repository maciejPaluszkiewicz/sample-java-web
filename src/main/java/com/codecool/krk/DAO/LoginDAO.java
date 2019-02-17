package com.codecool.krk.DAO;

public interface LoginDAO {

    boolean checkIfLoginAndPasswordAreCorrect(String login, String password) throws Exception;
}
