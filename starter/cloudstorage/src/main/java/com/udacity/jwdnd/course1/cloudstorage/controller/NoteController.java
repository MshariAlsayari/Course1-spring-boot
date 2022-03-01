package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("note")
public class NoteController {

    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping
    public String submitNote(Authentication authentication, @ModelAttribute("newNote") NoteForm newNote, Model model) {
        String userName = authentication.getName();


        if (newNote.getNoteId().isEmpty()){
            noteService.createNote(newNote.getTitle(), newNote.getDescription(),userName);
        }else {
            Note existingNote = getNote(Integer.parseInt(newNote.getNoteId()));
            noteService.updateNote(Integer.parseInt(newNote.getNoteId()), newNote.getTitle(), newNote.getDescription());
        }



        model.addAttribute("result", "success");
        return "result";
    }

    @GetMapping(value ="/deleteNote/{noteId}")
    public String deleteNote(@PathVariable Integer noteId, Model model) {
        noteService.deleteNote(noteId);
        model.addAttribute("result", "success");
        return "result";
    }

    public Note getNote(Integer noteId) {
        return noteService.getNote(noteId);
    }


}
