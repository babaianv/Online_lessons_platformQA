package com.learn.models;

public class ChangePassword {
    private  String oldPassword;
    private  String newPassword;
    private  String confirmPassword;

    public ChangePassword setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public ChangePassword setNewPassword(String newPassword) {
        this.newPassword = newPassword;
        return this;
    }

    public ChangePassword setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
