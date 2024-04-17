package com.learn.fw;

import com.learn.models.ChangePassword;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChangePasswordHelper extends BaseHelper {

    public ChangePasswordHelper(WebDriver driver) {
        super(driver);
    }

    public void clickSavePasswordBtn() {
        click(By.cssSelector("#save-btn"));
    }

    public void fillChangePasswordForm(ChangePassword changePassword) {
        type(By.cssSelector("#oldPassword"), changePassword.getOldPassword());
        type(By.cssSelector("#newPassword"), changePassword.getNewPassword());
        type(By.cssSelector("#confrimNewPassword"), changePassword.getConfirmPassword());
    }

    public void clickChangePasswordLink() {
        click(By.xpath("//button[text()='Change password']"));
    }

    public boolean isChangePasswordSuccessMessagePresent() {
        return isElementPresent(By.cssSelector("#success"));
    }

    public boolean isChangePasswordErrorMessagePresent() {
        return isElementPresent(By.cssSelector("#error"));
    }
}

