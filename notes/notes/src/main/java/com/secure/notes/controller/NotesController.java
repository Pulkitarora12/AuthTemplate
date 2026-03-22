package com.secure.notes.controller;

import com.secure.notes.entity.Note;
import com.secure.notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    private NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public ResponseEntity<Note> createNote(
            @RequestBody String content,
            @AuthenticationPrincipal UserDetails userDetails) {

        String ownerUsername = userDetails.getUsername();
        Note note = noteService.createNoteForUser(ownerUsername, content);
        return ResponseEntity.status(201).body(note);
    }

    @GetMapping
    public ResponseEntity<?> getUserNotes(@AuthenticationPrincipal UserDetails userDetails) {
        String ownerUsername = userDetails.getUsername();
        List<Note> list = noteService.getNotesForUser(ownerUsername);
        return ResponseEntity.status(200).body(list);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNote(@PathVariable Long noteId, @RequestBody String content,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        String ownerUsername = userDetails.getUsername();
        Note note = noteService.updateNoteForUser(noteId, content, ownerUsername);
        return ResponseEntity.status(200).body(note);
    }

    @DeleteMapping("{noteId}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long noteId, @AuthenticationPrincipal UserDetails userDetails) {
        String ownerUsername = userDetails.getUsername();
        noteService.deleteNoteForUser(noteId, ownerUsername);
        return ResponseEntity.noContent().build();
    }
}
