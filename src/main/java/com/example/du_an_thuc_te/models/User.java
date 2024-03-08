package com.example.du_an_thuc_te.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;
    @Column(name = "gender")
    private Boolean gender;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "fullName")
    private String fullName;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private Set<UserRole> userRoles;

    public User() {
    }

    public User(int id, String username, String password, Boolean enabled, Boolean gender, String address, String email, String telephone, String fullName, Set<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.fullName = fullName;
        this.userRoles = userRoles;
    }

    public User(String username, String password, Boolean enabled, Boolean gender, String address, String email, String telephone, String fullName) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.telephone = telephone;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
