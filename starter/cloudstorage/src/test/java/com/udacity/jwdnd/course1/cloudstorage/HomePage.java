package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id = "nav-files-tab")
    private WebElement navFilesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement navCredentialsTab;


    // Note
    @FindBy(id = "btn-add-note")
    private WebElement btnAddNote;

    @FindBy(id = "btn-edit-note")
    private WebElement btnEditNote;

    @FindBy(id = "btn-delete-note")
    private WebElement btnDeleteNote;

    @FindBy(id = "note-title")
    private WebElement editTextNoteTitle;

    @FindBy(id = "note-description")
    private WebElement editTextNoteDescription;

    @FindBy(id = "noteSubmit")
    private WebElement btnSubmitNote;

    @FindBy(id = "tableNoteTitle")
    private WebElement noteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement noteDescription;

    @FindBy(id = "exampleNoteTitle")
    private WebElement exampleNoteTitle;

    @FindBy(id = "exampleNoteDescription")
    private WebElement exampleNoteDescription;




    // Credential
    @FindBy(id = "btn-add-credential")
    private WebElement btnAddCredential;

    @FindBy(id = "btn-edit-credential")
    private WebElement btnEditCredential;

    @FindBy(id = "btn-delete-credential")
    private WebElement btnDeleteCredential;

    @FindBy(id = "credential-url")
    private WebElement editTextCredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement editTextCredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement editTextCredentialPassword;

    @FindBy(id = "credentialSubmit")
    private WebElement btnSubmitCredential;

    @FindBy(id = "tableCredentialUrl")
    private WebElement credentialUrl;

    @FindBy(id = "tableCredentialUsername")
    private WebElement credentialUsername;

    @FindBy(id = "tableCredentialPassword")
    private WebElement credentialPassword;

    @FindBy(id = "exampleCredentialUrl")
    private WebElement exampleCredentialUrl;

    @FindBy(id = "exampleCredentialUsername")
    private WebElement exampleCredentialUsername;

    @FindBy(id = "exampleCredentialPassword")
    private WebElement exampleCredentialPassword;



    private final JavascriptExecutor js;

    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 800);
    }


    public void navTo(Tabs tab) {

        switch (tab){
            case FILES:
                js.executeScript("arguments[0].click();", navFilesTab);
                break;
            case NOTES:
                js.executeScript("arguments[0].click();", navNotesTab);
                break;
            case CREDENTIALS:
                js.executeScript("arguments[0].click();", navCredentialsTab);
                break;
        }


    }

    //Note
    public void clickOnAddNoteButton() {
        js.executeScript("arguments[0].click();", btnAddNote);
    }

    public void setNoteTitle(String title) {
        js.executeScript("arguments[0].value='" + title + "';", editTextNoteTitle);
    }

    public void setNoteDescription(String description) {
        js.executeScript("arguments[0].value='" + description + "';", editTextNoteDescription);
    }

    public void clickOnSubmitNoteButton() {
        js.executeScript("arguments[0].click();", btnSubmitNote);
    }

    public void clickOnDeleteNoteButton() {
        js.executeScript("arguments[0].click();", btnDeleteNote);
    }

    public void clickOnEditNoteButton() {
        js.executeScript("arguments[0].click();", btnEditNote);
    }



    //Credential
    public void clickOnAddCredentialButton() {
        js.executeScript("arguments[0].click();", btnAddCredential);
    }

    public void setCredentialUrl(String url) {
        js.executeScript("arguments[0].value='" + url + "';", editTextCredentialUrl);
    }

    public void setCredentialUsername(String username) {
        js.executeScript("arguments[0].value='" + username + "';", editTextCredentialUsername);
    }

    public void setCredentialPassword(String password) {
        js.executeScript("arguments[0].value='" + password + "';", editTextCredentialPassword);
    }

    public void clickOnSubmitCredentialButton() {
        js.executeScript("arguments[0].click();", btnSubmitCredential);
    }

    public void clickOnDeleteCredentialButton() {
        js.executeScript("arguments[0].click();", btnDeleteCredential);
    }

    public void clickOnEditCredentialButton() {
        js.executeScript("arguments[0].click();", btnEditCredential);
    }


    public Note getNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).getText();
        String description = noteDescription.getText();
        return new Note(title, description);
    }

    public Note getExampleNote() {
        String title = wait.until(ExpectedConditions.elementToBeClickable(exampleNoteTitle)).getText();
        String description = exampleNoteDescription.getText();
        return new Note(title, description);
    }

    public Credential getCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(credentialUrl)).getText();
        String username =wait.until(ExpectedConditions.elementToBeClickable(credentialUsername)).getText();
        return new Credential(url, username,"");
    }

    public Credential getExampleCredential() {
        String url = wait.until(ExpectedConditions.elementToBeClickable(exampleCredentialUrl)).getText();
        String username =wait.until(ExpectedConditions.elementToBeClickable(exampleCredentialUsername)).getText();
        return new Credential(url, username, "");
    }



    enum Tabs{
        FILES,NOTES,CREDENTIALS
    }






}
