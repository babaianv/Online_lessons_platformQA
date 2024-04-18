package com.learn.fw;

import com.learn.models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public String getRegisterError() {
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));
        return errorElement.getText();
    }


    public void fillRegisterForm(User user) {
        type(By.cssSelector("#nickname"), user.getNickname());
        type(By.cssSelector("#email"), user.getEmail());
        type(By.cssSelector("#password"), user.getPassword());
    }

    public void clickSubmitSignUpBtn() {
        click(By.cssSelector("button.submit-reg-button"));
    }

    public void clickOnSignUpBtn() {
        click(By.cssSelector("a.signUpBtn[href='/reg']"));
    }

    public void clickOnDeleteAccountBtn() {
        click(By.cssSelector("#delete-btn"));
    }

    public void fillLoginForm(User user) {
    type(By.cssSelector("#email"), user.getEmail());
    type(By.cssSelector("#password"), user.getPassword());
    }

    public void clickSubmitLoginBtn() {
        click(By.cssSelector("button.submit-log-button"));
    }

    public void clickOnLoginBtn() {
        click(By.cssSelector("a.loginBtn[href='/log']"));
    }

    public boolean isLogoutLinkPresent() {
        return isElementPresent(By.cssSelector(".burgerMenuContent a[href='/']"));
    }

    public boolean isLoginPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'You logged in.')]"));
    }

    public boolean isDeleteAccountSuccessPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'User Testval deleted')]"));
    }

    public boolean isRegisterSuccessPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Account has been created and you are logged in.')]"));
    }

    public boolean isLoginErrorIncorrectPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Email or password is incorrect')]"));
    }

    public boolean isLoginErrorEmptyPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Email and password must not be empty')]"));
    }

    public boolean isSubmitSignUpBtnPresent() {
        return isElementPresent(By.cssSelector("button.submit-reg-button"));
    }

    public boolean isNicknameTakenErrorPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'This nickname is already taken')]"));
    }

    public boolean isEmailExistErrorPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'User with this email already exists')]"));
    }

    public void clickBurgerMenuLogoutBtn() {
        click(By.cssSelector(".burgerMenu"));
        click(By.cssSelector(".burgerMenuContent a[href='/']"));
    }

    public void clickOnBurgerMenuMyAccount() {
        click(By.cssSelector(".burgerMenu"));
        click(By.cssSelector(".burgerMenuContent a[href='/my_account']"));
    }

    public void clickBurgerMenu() {
        click(By.cssSelector(".burgerMenu"));
    }

    public void clickAccountInfo() {
        click(By.xpath("//button[contains(text(),'Account info')]"));
    }

    public boolean isBurgerMenuPresent() {
        return isElementPresent(By.cssSelector(".burgerMenu"));
    }
    public boolean isLogoutSuccessPopUpPresent(){
        return isElementPresent(By.cssSelector("//div[contains(text(), 'You logged out.')]"));
    }

    public boolean isLoginBtnPresent(){
        return isElementPresent(By.cssSelector("a.loginBtn[href='/log']"));
    }
}

