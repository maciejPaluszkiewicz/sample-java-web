package com.codecool.krk.DAO;

import com.codecool.krk.model.Message;

import java.util.List;

public interface MessageDAO {

    List<Message> getAllMessages() throws Exception;

    void addNewMessage(Message message) throws Exception;
}
