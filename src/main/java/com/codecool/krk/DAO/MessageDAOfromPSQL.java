package com.codecool.krk.DAO;

import com.codecool.krk.model.Message;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOfromPSQL implements MessageDAO {

    DataBaseConnector dbcon = new DataBaseConnector();

    @Override
    public List<Message> getAllMessages() throws Exception {
        List<Message> result = new ArrayList<>();
        String query = "SELECT * FROM messages";

        ResultSet rs = dbcon.query(query, new String[0]);

        while(rs.next()){
            result.add(new Message(rs.getString("user"), rs.getDate("date"),
                    rs.getString("text")));
        }

        return result;
    }

    @Override
    public void addNewMessage(Message message) throws Exception{
        String query = "INSERT INTO messages (\"user\", \"date\", \"text\") VALUES (?, ?::date, ?);";
        String[] queryAttr = {message.getUser(), message.getDate().toString(), message.getText()};
        dbcon.updateSQL(query, queryAttr);

    }
}
//String.format("%1$tb %1$te, %1$tY %1$tI:%1$tM %1$Tp", date)
