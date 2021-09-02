package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Messages;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model.Users;

import java.util.LinkedList;

public interface IMessageService {
    public LinkedList<Messages> getMessage(Users user);
    public void setMessage(String recipient, Messages message);
}
