package by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage;

import by.it_academy.jd2.Mk_JD2_82_21.jsp_homework.storage.model.Messages;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class MessageStorage {




    private static final MessageStorage instance =new MessageStorage();
    private  Map<String, LinkedList<Messages>> messageHistory ;

    {
        this.messageHistory = new HashMap<>();
    }


    public MessageStorage() {

    }

    public static MessageStorage getInstance() {
        return instance;
    }



    public Map<String, LinkedList<Messages>> getUserList() {
        return messageHistory;
    }



}
