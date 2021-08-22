package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.MessageStorage;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Messages;
import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Users;

import java.util.LinkedList;

public class MessageService {
    private static MessageService instance = new MessageService();

    public static MessageService getInstance() {
        return instance;
    }

    public void newRecipient(String recipient) {
        MessageStorage.getInstance().getUserList().put(recipient,new LinkedList<Messages>());
    }

    public void setMessage(String recipient, Messages message) {
        if(!MessageStorage.getInstance().getUserList().containsKey(recipient)) {
            newRecipient(recipient);
        }
        MessageStorage.getInstance().getUserList().get(recipient).add(message);
    }

    public LinkedList<Messages> getMessage(Users user) {
       return MessageStorage.getInstance().getUserList().get(user.getLogin());
    }
}
