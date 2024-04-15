package com.learn.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class ApplicationManager {
    static WebDriver driver;
    String browser;
    UserHelper userHelper;
    CourseHelper courseHelper;
    ChangePasswordHelper changePasswordHelper;
    CartHelper cartHelper;
    HomePageHelper homePageHelper;
    LessonHelper lessonHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {

        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if (browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }

        driver.get("http://localhost:5173/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        userHelper = new UserHelper(driver);
        courseHelper = new CourseHelper(driver);
        changePasswordHelper = new ChangePasswordHelper(driver);
        cartHelper = new CartHelper(driver);
        homePageHelper = new HomePageHelper(driver);
        lessonHelper = new LessonHelper(driver);
    }

    public void stop() {
        driver.quit();
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public CourseHelper getCourseHelper() {
        return courseHelper;
    }

    public ChangePasswordHelper getChangePasswordHelper() {
        return changePasswordHelper;
    }

    public CartHelper getCartHelper() {
        return cartHelper;
    }

    public HomePageHelper getHomePageHelper() {
        return homePageHelper;
    }

    public LessonHelper getLessonHelper() {
        return lessonHelper;
    }
}

