package com.learn.UItests.Negative;

import com.learn.UItests.TestBase;
import com.learn.models.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegistrationNegativeTests extends TestBase {

    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void precondition(){
        app.getUserHelper().pause(5000);
    }

    @Test(description = "UI: Registration with exists Nickname Test",priority = 1)
    public void regWithExistNicknameNegativeTest(){

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

    @Test(description = "UI: Reg With Short Length Nickname Neg Test",priority = 2)
    public void regWithShortLengthNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Te")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();
        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With Long Length Nickname Neg Test",priority = 3)
    public void regWithLongLengthNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testnegat123")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only Numbers Nickname Neg Test",priority = 4)
    public void regWithOnlyNumbersNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("12345")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With invalid Symbol Nickname Neg Test",priority = 5)
    public void regWithInvalidSymbolNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Test#$")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Only Symbol Nickname Neg Test",priority = 6)
    public void regWithOnlySymbolNicknameNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("*#$%)#")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

//    @Test(description = "UI: Reg With Empty Nickname Neg Test",priority = 7)
//    public void regWithEmptyNicknameNegTest(){
//        app.getUserHelper().clickOnSignUpBtn();
//        app.getUserHelper().fillRegisterForm(new User()
//                .setEmail("test11@gmail.com")
//                .setPassword("Test1test1!"));
//        app.getUserHelper().clickSubmitSignUpBtn();
//
//        softAssert.assertEquals( "Invalid nickname format", app.getUserHelper().getRegisterError());
//        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
//        softAssert.assertAll();
//    }


    @Test(description = "UI: Reg With Exist Email Neg Test",priority = 8)
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

//    @Test(description = "UI: Reg With empty Email Neg Test",priority = 9)
//    public void regWithEmptyEmailNegTest(){
//        app.getUserHelper().clickOnSignUpBtn();
//        app.getUserHelper().fillRegisterForm(new User()
//                .setNickname("Testreg18")
//                .setPassword("Test1test1!"));
//        app.getUserHelper().clickSubmitSignUpBtn();
//
//        softAssert.assertEquals( "Invalid email format", app.getUserHelper().getRegisterError());
//        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
//        softAssert.assertAll();
//    }

    @Test(description = "UI: Reg With wrong Email Format Neg Test",priority = 10)
    public void regWithWrongEmailFormatNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg18")
                .setEmail("test11gmail.com")
                .setPassword("Test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid email format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }



//    @Test(description = "UI: Reg With empty Password Neg Test",priority = 11)
//    public void regWithEmptyPasswordNegTest(){
//        app.getUserHelper().clickOnSignUpBtn();
//        app.getUserHelper().fillRegisterForm(new User()
//                .setNickname("Testreg18")
//                .setEmail("test11@gmail.com"));
//        app.getUserHelper().clickSubmitSignUpBtn();
//
//        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
//        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
//        softAssert.assertAll();
//    }

    @Test(description = "UI: Reg With short Password Neg Test",priority = 12)
    public void regWithShortPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg21")
                .setEmail("test11@gmail.com")
                .setPassword("Test12!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only Numbers Password Neg Test",priority = 13)
    public void regWithOnlyNumbersPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("123456789"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With only Letters Password Neg Test",priority = 14)
    public void regWithOnlyLettersPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("Testtest"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only valid symbol Password Neg Test",priority = 15)
    public void regWithOnlyValidSymbolPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("!$#%!$#%"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With only invalid symbol Password Neg Test",priority = 16)
    public void regWithOnlyValidInSymbolPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("*@,.)&?-="));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }


    @Test(description = "UI: Reg With Only Uppercase Password Neg Test",priority = 17)
    public void regWithOnlyUppercasePasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("TEST1TEST1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg With Only Lowercase Password Neg Test",priority = 18)
    public void regWithOnlyLowercasePasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("test1test1!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg Without Special Symbol Password Neg Test",priority = 19)
    public void regWithoutSpecialSymbolPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("Test1test1"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }

    @Test(description = "UI: Reg Without Numbers Symbol Password Neg Test",priority = 20)
    public void regWithoutNumbersPasswordNegTest(){
        app.getUserHelper().clickOnSignUpBtn();
        app.getUserHelper().fillRegisterForm(new User()
                .setNickname("Testreg22")
                .setEmail("test11@gmail.com")
                .setPassword("Testtest!"));
        app.getUserHelper().clickSubmitSignUpBtn();

        softAssert.assertEquals( "Invalid password format", app.getUserHelper().getRegisterError());
        softAssert.assertTrue(app.getUserHelper().isSubmitSignUpBtnPresent());
        softAssert.assertAll();
    }
}

