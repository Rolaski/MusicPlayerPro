package com.example.musicplayerpro.files;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User
{
    @Id
    @Column(name = "idUser")
    private int idUser;
    private String userName;
    private String userPassword;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User() {
    }

    public User(int idUser, String userName, String userPassword, UserType userType)
    {
        this.idUser = idUser;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userType = userType;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
