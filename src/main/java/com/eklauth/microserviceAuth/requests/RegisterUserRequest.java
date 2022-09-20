/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eklauth.microserviceAuth.requests;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


/**
 *
 * @author isaac
 */

public class RegisterUserRequest implements Serializable {

    @NotEmpty(message="Username is required")
    private String username;
    @NotEmpty(message="Password is required")
    private String password;
    private String email;
    private int createdAt;

    @Column(name = "fullnames")
    private String fullNames;
    private String phone;
    private String idNo;
    private String gender;

    private boolean status;

    private String registerType;

    public RegisterUserRequest() {
    }

    public RegisterUserRequest(String username, String password, String email, int createdAt, String fullNames, String phone, String idNo, String gender, boolean status,String registerType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.fullNames = fullNames;
        this.phone = phone;
        this.idNo = idNo;
        this.gender = gender;
        this.status= status;
        this.registerType=registerType;
    }

//@NotBlank

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }
}
