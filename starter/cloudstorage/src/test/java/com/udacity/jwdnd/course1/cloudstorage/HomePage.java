package com.udacity.jwdnd.course1.cloudstorage;


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



    enum Tabs{
        FILES,NOTES,CREDENTIALS
    }






}
