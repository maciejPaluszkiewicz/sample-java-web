package com.codecool.krk.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOfromPSQL implements LoginDAO{

    private DataBaseConnector dbcon = new DataBaseConnector();

    @Override
    public boolean checkIfLoginAndPasswordAreCorrect(String login, String hashPassword) throws SQLException {
        String query = "SELECT * FROM logins WHERE login = ? AND password = ?";
        String[] attrs = {login, hashPassword};

        ResultSet rs = dbcon.query(query, attrs);

        return rs.next();
    }
}
