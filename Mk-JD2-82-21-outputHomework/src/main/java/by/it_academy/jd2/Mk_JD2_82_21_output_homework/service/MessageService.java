package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.IMessageService;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.MessageStorage;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Messages;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import java.util.LinkedList;

public class MessageService implements IMessageService {
    private static MessageService instance = new MessageService();

    @Override
    public void setMessage(String recipient, Messages message) {
        if(!MessageStorage.getInstance().getMessageList().containsKey(recipient)) {
             newRecipient(recipient);
        }
        MessageStorage.getInstance().getMessageList().get(recipient).add(message);
    }
    @Override
    public LinkedList<Messages> getMessage(Users user) {
       return MessageStorage.getInstance().getMessageList().get(user.getLogin());
    }

    public static MessageService getInstance() {
        return instance;
    }

    public void newRecipient(String recipient) {
        MessageStorage.getInstance().getMessageList().put(recipient,new LinkedList<Messages>());
    }
}
