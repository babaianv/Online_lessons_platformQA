package com.learn.UItests;

import com.learn.fw.ApplicationManager;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

    protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", Browser.CHROME.browserName()));

    @BeforeSuite
    public void setUp(){
        app.init();
    }

    @AfterSuite
    public void tearDown(){
        app.stop();
    }


}

