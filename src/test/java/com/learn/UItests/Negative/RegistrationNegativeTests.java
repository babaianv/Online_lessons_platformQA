package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @Test(description = "UI: Registration with exists Nickname Test")
    public void regWithExistNicknameNegativeTest(){
        app.getUserHelper().pause(2000);
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Test1")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertTrue(app.getUserHelper().isNicknameTakenErrorPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Short Length Nickname Neg Test")
    public void regWithShortLengthNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Te")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With Long Length Nickname Neg Test")
    public void regWithLongLengthNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testnegat123")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only Numbers Nickname Neg Test")
    public void regWithOnlyNumbersNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("12345")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With invalid Symbol Nickname Neg Test")
    public void regWithInvalidSymbolNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Test#$")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Only Symbol Nickname Neg Test")
    public void regWithOnlySymbolNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("*#$%)#")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Empty Nickname Neg Test")
    public void regWithEmptyNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

                         ///Email

    @Test(description = "UI: Reg With Exist Email Neg Test")
    public void regWithExistEmailNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg17")
                .setEmail("test1@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertTrue(app.getUserHelper().isEmailExistErrorPopUpPresent());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With empty Email Neg Test")
    public void regWithEmptyEmailNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg18")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid email format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With wrong Email Format Neg Test")
    public void regWithWrongEmailFormatNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg18")
                .setEmail("test11gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid email format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

                        //Password

    @Test(description = "UI: Reg With empty Password Neg Test")
    public void regWithEmptyPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg18")
                .setEmail("test11@gmail.com"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With short Password Neg Test")
    public void regWithShortPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg21")
                .setEmail("test11@gmail.com")
                .setPassword("Test12!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only Numbers Password Neg Test")
    public void regWithOnlyNumbersPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("123456789"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With only Letters Password Neg Test")
    public void regWithOnlyLettersPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("Testtest"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only valid symbol Password Neg Test")
    public void regWithOnlyValidSymbolPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("!$#%!$#%"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only invalid symbol Password Neg Test")
    public void regWithOnlyValidInSymbolPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("*@,.)&?-="));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With Only Uppercase Password Neg Test")
    public void regWithOnlyUppercasePasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("TEST1TEST1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Only Lowercase Password Neg Test")
    public void regWithOnlyLowercasePasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg Without Special Symbol Password Neg Test")
    public void regWithoutSpecialSymbolPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg Without Numbers Symbol Password Neg Test")
    public void regWithoutNumbersPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("Testtest!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError().getText());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }
}

