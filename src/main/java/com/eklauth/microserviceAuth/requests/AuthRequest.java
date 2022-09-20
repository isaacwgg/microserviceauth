/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eklauth.microserviceAuth.requests;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author isaac
 */
public class AuthRequest {
    @NotEmpty(message="UserName cannot be null or empty")
    private String userName;
     @NotEmpty(message="Password cannot be null or empty")
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }


}
