package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();


    @Test(description = "UI: Registration with exists Nickname Test")
    public void regWithExistNicknameNegativeTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Test1");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test11@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'This nickname is already taken')]")));
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Short Length Nickname Neg Test")
    public void regWithShortLengthNicknameNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Te");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregn12@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid nickname format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Long Length Nickname Neg Test")
    public void regWithLongLengthNicknameNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testnegat123");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregn13@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid nickname format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only Numbers Nickname Neg Test")
    public void regWithOnlyNumbersNicknameNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("12345");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregn14@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid nickname format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With invalid Symbol Nickname Neg Test")
    public void regWithInvalidSymbolNicknameNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Test#$");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregn15@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid nickname format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Only Symbol Nickname Neg Test")
    public void regWithOnlySymbolNicknameNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("*#$%)#");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregn16@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid nickname format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Empty Nickname Neg Test")
    public void regWithEmptyNicknameNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testregn16@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid nickname format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

                         ///Email

    @Test(description = "UI: Reg With Exist Email Neg Test")
    public void regWithExistEmailNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg17");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test1@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        softAssert.assertTrue(isElementPresent(By.xpath("//div[contains(text(), 'User with this email already exists')]")));
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With empty Email Neg Test")
    public void regWithEmptyEmailNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg18");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid email format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With wrong Email Format Neg Test")
    public void regWithWrongEmailFormatNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg19");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test19gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid email format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

                        //Password

    @Test(description = "UI: Reg With empty Password Neg Test")
    public void regWithEmptyPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg20");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test20@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With short Password Neg Test")
    public void regWithShortPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg21");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test21@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test12!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only Numbers Password Neg Test")
    public void regWithOnlyNumbersPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg22");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test22@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("123456789");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With only Letters Password Neg Test")
    public void regWithOnlyLettersPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg23");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test23@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Testtest");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();

        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only valid symbol Password Neg Test")
    public void regWithOnlyValidSymbolPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg24");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test24@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("!$#%!$#%");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only invalid symbol Password Neg Test")
    public void regWithOnlyValidInSymbolPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg25");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test25@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("*@,.)&?-=");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With Only Uppercase Password Neg Test")
    public void regWithOnlyUppercasePasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg26");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test26@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("TEST1TEST1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Only Lowercase Password Neg Test")
    public void regWithOnlyLowercasePasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg27");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test27@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg Without Special Symbol Password Neg Test")
    public void regWithoutSpecialSymbolPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg28");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test28@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg Without Numbers Symbol Password Neg Test")
    public void regWithoutNumbersPasswordNegTest(){
        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();

        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testreg29");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("test29@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Testtest!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
        WebElement errorElement = driver.findElement(By.className("registration-error-message"));

        softAssert.assertEquals( "Invalid password format", errorElement.getText());
        softAssert.assertTrue(isElementPresent(By.cssSelector("button.submit-reg-button")));
        softAssert.assertFalse(isElementPresent(By.cssSelector(".burgerMenu")));
        softAssert.assertAll();
    }
}

