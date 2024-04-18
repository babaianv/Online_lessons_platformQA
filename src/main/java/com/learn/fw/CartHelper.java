package com.learn.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartHelper extends BaseHelper {

    public CartHelper(WebDriver driver) {
        super(driver);
    }

    public void clickRemoveOneItemFromCartBtn() {
        click(By.cssSelector(".removeBtn"));
    }

    public void clickRemoveAllItemsFromCartBtn() {
        click(By.className("removeAllBtn"));
    }

    public void clickCartIcon() {
        click(By.cssSelector(".cartIcon"));
    }

    public void clickAddToCartBtn() {
        click(By.cssSelector(".addToCartBtn"));
    }

    public void clickOnCourseCard() {
        click(By.cssSelector("a[href='/courses/2']"));
    }

    public boolean isRemoveCourseFromCartSuccessPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Done!')]"));
    }

    public void clickOnPayNowBtn() {
        click(By.cssSelector(".payNowBtn"));
    }

    public void clickOnPayCheckbox() {
        click(By.cssSelector("#paypalCheckbox"));
    }

    public boolean isPaymentSuccessPopUpPresent() {
        return isElementPresent(By.xpath("//div[contains(text(), 'Payment successful!')]"));
    }
}

