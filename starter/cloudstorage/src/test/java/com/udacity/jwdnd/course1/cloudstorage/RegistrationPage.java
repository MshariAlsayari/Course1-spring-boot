package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    @FindBy(id = "inputFirstName")
    private WebElement editTextFirstName;

    @FindBy(id = "inputLastName")
    private WebElement editTextLastName;

    @FindBy(id = "inputUsername")
    private WebElement editTextUserName;

    @FindBy(id = "inputPassword")
    private WebElement editTextPassword;

    @FindBy(id = "buttonSignUp")
    private WebElement btnRegister;


    private final JavascriptExecutor js;

    public RegistrationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }


    public void setFirstName(String firstName) {
        js.executeScript("arguments[0].value='" + firstName + "';", editTextFirstName);
    }

    public void setLastName(String lastName) {
        js.executeScript("arguments[0].value='" + lastName + "';", editTextLastName);
    }

    public void setUserName(String userName) {
        js.executeScript("arguments[0].value='" + userName + "';", editTextUserName);
    }

    public void setPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", editTextPassword);
    }

    public void clickBtnRegister() {
        js.executeScript("arguments[0].click();", btnRegister);
    }
}
