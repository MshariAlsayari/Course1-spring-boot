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
        homePage.navTo(HomePage.Tabs.NOTES);
        homePage = new HomePage(driver);
        Note note = homePage.getNote();
        Assertions.assertEquals(noteTitle, note.getNoteTitle());
        Assertions.assertEquals(noteDescription, note.getNoteDescription());
    }









    public void initNote(String title, String description, HomePage page){
        page.navTo(HomePage.Tabs.NOTES);
        page.clickOnAddNoteButton();
        page.setNoteTitle(title);
        page.setNoteDescription(description);
        page.clickOnSubmitNoteButton();
        ResultPage resultPage = new ResultPage(driver);
        resultPage.clickBtnSuccessResult();
        page.navTo(HomePage.Tabs.NOTES);
    }









}
