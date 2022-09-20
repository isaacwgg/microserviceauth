/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eklauth.microserviceAuth.responses;

/**
 *
 * @author isaac
 */
public class UserView extends BasicResponse{
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    

    public static class Data {
       private String accessToken;
       private String tokenType = "Bearer";
       private String userName;
       private String fullName;
       private Object[] authorities;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Object[] getAuthorities() {
            return authorities;
        }

        public void setAuthorities(Object[] authorities) {
            this.authorities = authorities;
        }
    
    }
    
}
