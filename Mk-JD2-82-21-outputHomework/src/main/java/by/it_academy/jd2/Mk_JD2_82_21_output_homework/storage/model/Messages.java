package by.it_academy.jd2.Mk_JD2_82_21_output_homework.storage.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Messages implements Serializable {
    private Users sender;
    private String messageText;
    private final LocalDateTime messageSendingTime;

    public Messages(Users sender, String messageText, LocalDateTime messageSendingTime) {
        this.sender = sender;
        this.messageText = messageText;
        this.messageSendingTime = messageSendingTime;
    }

    public Users getSender() {
        return sender;
    }

    public String getMessageText() {
        return messageText;
    }

    public LocalDateTime getMessageSendingTime() {
        return messageSendingTime;
    }
}
