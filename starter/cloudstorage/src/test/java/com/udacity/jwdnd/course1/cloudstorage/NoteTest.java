package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest extends CloudStorageApplicationTests {


    @Test
    public void teat_create_note() {
        String noteTitle = "first note";
        String noteDescription = "this is my first note.";
        HomePage homePage = doRegistrationAndLogin();
        initNote(noteTitle, noteDescription, homePage);
        homePage = new HomePage(driver);
        Note note = homePage.getNote();
        Assertions.assertEquals(noteTitle, note.getNoteTitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());
    }

    @Test
    public void teat_delete_note() {
        String noteTitle = "first note";
        String noteDescription = "this is my first note.";
        HomePage homePage = doRegistrationAndLogin();
        initNote(noteTitle, noteDescription, homePage);
        homePage = new HomePage(driver);
        deleteNote(homePage);
        Note note = homePage.getExampleNote();
        Assertions.assertEquals("Example Note Title", note.getNoteTitle());
        Assertions.assertEquals("Example Note Description", note.getNoteDescription());
    }

    @Test
    public void teat_edit_note() {
        String noteTitle = "first note";
        String noteDescription = "this is my first note.";
        String updatedNoteTitle = "Updated first note";
        String updatedNoteDescription = "this is my Updated first note.";
        HomePage homePage = doRegistrationAndLogin();
        initNote(noteTitle, noteDescription, homePage);
        homePage = new HomePage(driver);
        editNote(updatedNoteTitle, updatedNoteDescription,homePage);
        Note note = homePage.getNote();
        Assertions.assertEquals(updatedNoteTitle, note.getNoteTitle());
        Assertions.assertEquals(updatedNoteDescription, note.getNoteDescription());
        deleteNote(homePage);
    }


    private void initNote(String title, String description, HomePage page){
        page.navTo(HomePage.Tabs.NOTES);
        page.clickOnAddNoteButton();
        page.setNoteTitle(title);
        page.setNoteDescription(description);
        page.clickOnSubmitNoteButton();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickBtnSuccessResult();
        page.navTo(HomePage.Tabs.NOTES);
    }

    private void deleteNote(HomePage page){
        page.navTo(HomePage.Tabs.NOTES);
        page.clickOnDeleteNoteButton();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickBtnSuccessResult();
        page.navTo(HomePage.Tabs.NOTES);
    }

    private void editNote(String title, String description, HomePage page){
        page.navTo(HomePage.Tabs.NOTES);
        page.clickOnEditNoteButton();
        page.setNoteTitle(title);
        page.setNoteDescription(description);
        page.clickOnSubmitNoteButton();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickBtnSuccessResult();
        page.navTo(HomePage.Tabs.NOTES);
    }









}
