package com.secure.notes.service.impl;

import com.secure.notes.entity.Note;
import com.secure.notes.repository.NoteRepository;
import com.secure.notes.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note createNoteForUser(String userName, String content) {
        Note note = new Note();
        note.setOwnerUserName(userName);
        note.setContent(content);
        return note;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content) {
        Note note = noteRepository.findById(noteId).
                orElseThrow(() -> new RuntimeException("Note not found"));
        note.setContent(content);
        noteRepository.save(note);
        return note;
    }

    @Override
    public void deleteNoteForUser(Long noteId) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public List<Note> getNotesForUser(String userName) {
        return noteRepository.findByOwnerUserName(userName);
    }
}
