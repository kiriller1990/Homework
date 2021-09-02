package by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Messages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MessageStorage {
    private static final MessageStorage instance =new MessageStorage();
    private  Map<String, LinkedList<Messages>> messageList ;

    {
        this.messageList = new HashMap<>();
    }

    private MessageStorage() {

    }

    public static MessageStorage getInstance() {
        return instance;
    }

    public Map<String, LinkedList<Messages>> getMessageList() {
        return messageList;
    }



}
