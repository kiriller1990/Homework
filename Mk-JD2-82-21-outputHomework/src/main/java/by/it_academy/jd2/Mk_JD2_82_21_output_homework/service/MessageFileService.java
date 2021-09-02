package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.IMessageService;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.MessageStorage;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Messages;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings("unchecked")
public class MessageFileService implements IMessageService {
    private static MessageFileService instance = new MessageFileService();
    private static final String MESSAGE_FILE_PASS = "messages.ser";

    @Override
    public LinkedList<Messages> getMessage(Users user) {
        return getMessageMap().get(user.getLogin());
    }

    public Map<String,LinkedList<Messages>> getMessageMap() {
        Map<String, LinkedList<Messages>> messagesMap = new HashMap<>();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MESSAGE_FILE_PASS))){
             messagesMap = (Map<String, LinkedList<Messages>>) ois.readObject();
            } catch (Exception ex) {
            System.out.println(ex.getMessage());
            }
        return messagesMap;
    }

    @Override
    public void setMessage(String recipient, Messages message) {
        if(MessageStorage.getInstance().getMessageList().isEmpty()) {
            MessageStorage.getInstance().getMessageList().putAll(getMessageMap());
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MESSAGE_FILE_PASS))) {
            MessageService.getInstance().setMessage(recipient, message);
            oos.writeObject(MessageStorage.getInstance().getMessageList());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MessageFileService getInstance() {
        return instance;
    }
}
