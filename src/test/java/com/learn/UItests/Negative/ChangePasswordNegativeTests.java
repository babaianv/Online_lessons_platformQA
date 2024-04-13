package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ChangePasswordNegativeTests extends TestBase {

    @BeforeMethod
    public void precondition(){

        driver.findElement(By.cssSelector("a.signUpBtn[href='/reg']")).click();
        driver.findElement(By.cssSelector("#nickname")).click();
        driver.findElement(By.cssSelector("#nickname")).clear();
        driver.findElement(By.cssSelector("#nickname")).sendKeys("Testcpn");

        driver.findElement(By.cssSelector("#email")).click();
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("testpcn@gmail.com");

        driver.findElement(By.cssSelector("#password")).click();
        driver.findElement(By.cssSelector("#password")).clear();
        driver.findElement(By.cssSelector("#password")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("button.submit-reg-button")).click();
    }

    @AfterMethod
    public void clean(){
        driver.findElement(By.xpath("//button[contains(text(),'Account info')]")).click();

        driver.findElement(By.cssSelector("#delete-btn")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(description = "UI: Change Password with wrong old password Negative Test")
    public void changePasswordWithWrongOldPasswordNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Testtest!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with empty old password Negative Test")
    public void changePasswordWithEmptyOldPasswordNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with empty new password Negative Test")
    public void changePasswordWithEmptyNewPasswordNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }


    @Test(description = "UI: Change Password with wrong new password Negative Test")
    public void changePasswordWithWrongNewPasswordNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test123");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with wrong confirm password Negative Test")
    public void changePasswordWithWrongConfirmPasswordNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test123");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with Empty confirm password Negative Test")
    public void changePasswordWithEmptyConfirmPasswordNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test2test2!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with Only numbers Negative Test")
    public void changePasswordWithOnlyNumbersNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("123456789");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("123456789");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with Only letters Negative Test")
    public void changePasswordWithOnlyLettersNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Testtest");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Testtest");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with Only valid Symbol Negative Test")
    public void changePasswordWithOnlySymbolNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("!$#%!$#%!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("!$#%!$#%!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with invalid Symbol Negative Test")
    public void changePasswordWithInvalidSymbolNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test1test1>");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test1test1>");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with only Lowercase Negative Test")
    public void changePasswordWithOnlyLowercaseNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("test1test1!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("test1test1!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password with only Uppercase Negative Test")
    public void changePasswordWithOnlyUppercaseNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("TEST1TEST1!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("TEST1TEST1!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password Without Special Symbol Negative Test")
    public void changePasswordWithoutSpecialSymbolNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Test1test1");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Test1test1");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }

    @Test(description = "UI: Change Password Without Number Negative Test")
    public void changePasswordWithoutNumberNegativeTest(){
        driver.findElement(By.cssSelector(".burgerMenu")).click();
        driver.findElement(By.cssSelector(".burgerMenuContent a[href='/my_account']")).click();
        driver.findElement(By.xpath("//button[text()='Change password']")).click();

        driver.findElement(By.cssSelector("#oldPassword")).click();
        driver.findElement(By.cssSelector("#oldPassword")).clear();
        driver.findElement(By.cssSelector("#oldPassword")).sendKeys("Test1test1!");

        driver.findElement(By.cssSelector("#newPassword")).click();
        driver.findElement(By.cssSelector("#newPassword")).clear();
        driver.findElement(By.cssSelector("#newPassword")).sendKeys("Testtest!");

        driver.findElement(By.cssSelector("#confrimNewPassword")).click();
        driver.findElement(By.cssSelector("#confrimNewPassword")).clear();
        driver.findElement(By.cssSelector("#confrimNewPassword")).sendKeys("Testtest!");

        driver.findElement(By.cssSelector("#save-btn")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("#error")));
    }


}

