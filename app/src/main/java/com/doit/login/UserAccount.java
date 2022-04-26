package com.doit.login;

import android.provider.ContactsContract;

/*
 사용자 계정 정보 모델 클래스
 */
public class UserAccount {
    private String emailId;
    private String password;
    private String nickname;
    private String idToken; // Firebase Uid 고유정보

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public UserAccount() { }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
