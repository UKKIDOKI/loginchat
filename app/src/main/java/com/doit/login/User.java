package com.doit.login;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.stream.Stream;

public class User {
    String email;
    String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}
