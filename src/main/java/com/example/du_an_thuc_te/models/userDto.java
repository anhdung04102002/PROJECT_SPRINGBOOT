package com.example.du_an_thuc_te.models;

public class userDto {
    private String username;
    private String password;
    private Boolean enable;
    private Boolean gender;
    private String address;
    private String email;

    private String telephone;
    private String fullname;


    public userDto(String username, String password, Boolean enable, Boolean gender, String address, String email, String telephone, String fullname) {
        this.username = username;
        this.password = password;
        this.enable = enable;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.fullname = fullname;
    }

    public userDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
