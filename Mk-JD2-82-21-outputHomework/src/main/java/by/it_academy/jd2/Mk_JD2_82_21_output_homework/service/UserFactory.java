package by.it_academy.jd2.Mk_JD2_82_21_output_homework.service;

import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.EStorageType;
import by.it_academy.jd2.Mk_JD2_82_21_output_homework.service.api.IUserService;

public class UserFactory {
    private static EStorageType type = null;

    private UserFactory() {
    }

    public static synchronized void setType(EStorageType type) {
        if(type != null){
            UserFactory.type = type;
        } else {
            throw new IllegalStateException("Нельзя менять тип хранилища");
        }
    }

    public static IUserService getInstance(){
        if(type == null){
            throw new IllegalStateException("Тип хранилища не задан");
        }

        switch (type){
            case MEMORY:
                return UserStorageService.getInstance();
            case FILE:
                return UserStorageFileService.getInstance();
            default:
                throw new IllegalStateException("Неизвестный тип хранилища сообщений");
        }
    }
}
