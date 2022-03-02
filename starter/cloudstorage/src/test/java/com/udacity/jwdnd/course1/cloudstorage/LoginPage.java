package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy(id = "inputUsername")
    private WebElement editTextUserName;

    @FindBy(id = "inputPassword")
    private WebElement editTextPassword;

    @FindBy(id = "login-button")
    private WebElement btnLogin;

    private final JavascriptExecutor js;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }


    public void setUserName(String userName) {
        js.executeScript("arguments[0].value='" + userName + "';", editTextUserName);
    }

    public void setPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", editTextPassword);
    }

    public void clickBtnLogin() {
        js.executeScript("arguments[0].click();", btnLogin);
    }
}
