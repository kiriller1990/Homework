package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.IMessageService;

public class MessageFactory {
    private static EStorageType type = null;

    private MessageFactory() {
    }

    public static synchronized void setType(EStorageType type) {
        if(type != null){
            MessageFactory.type = type;
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public static IMessageService getInstance(){
        if(type == null){
            throw new IllegalStateException("Тип хранилища не задан");
        }

        switch (type){
            case MEMORY:
                return MessageService.getInstance();
            case FILE:
                return MessageFileService.getInstance();
            default:
                throw new IllegalStateException("Неизвестный тип хранилища сообщений");
        }
    }
}
