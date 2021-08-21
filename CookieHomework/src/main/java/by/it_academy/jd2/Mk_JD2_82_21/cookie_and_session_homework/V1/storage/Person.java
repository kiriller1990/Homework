package by.it_academy.jd2.Mk_JD2_82_21.cookie_and_session_homework.V1.storage;


public class Person {
    private String firstName;
    private String secondName;
    private String age;

    public Person(String firstName, String secondName, String age) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getAge() {
        return age;
    }
}

