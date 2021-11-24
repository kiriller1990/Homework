package by.it_academy.jd2.Mk_JD2_82_21.final_project.dto;

import by.it_academy.jd2.Mk_JD2_82_21.final_project.storage.model.User;

public class UserDTO {
    private User user;
    private String token;

    public UserDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
