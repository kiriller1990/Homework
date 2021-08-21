package by.it_academy.jd2.Mk_JD2_82_21.servlet_homework.service;

public class JobConditionsException extends Exception {
    public JobConditionsException() {
        super("Количество выбранных жанров не совпадает с условием задачи! Пожалуйста,выберите от 3 до 5 жанров.");
    }
}