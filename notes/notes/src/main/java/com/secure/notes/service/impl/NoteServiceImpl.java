package com.secure.notes.service.impl;

import com.secure.notes.entity.Note;
import com.secure.notes.repository.NoteRepository;
import com.secure.notes.service.AuditLogService;
import com.secure.notes.service.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    private AuditLogService  auditLogService;

    public NoteServiceImpl(NoteRepository noteRepository,AuditLogService auditLogService) {
        this.noteRepository = noteRepository;
        this.auditLogService = auditLogService;
    }

    @Override
    public Note createNoteForUser(String userName, String content) {
        Note note = new Note();
        note.setOwnerUserName(userName);
        note.setContent(content);
        noteRepository.save(note);
        auditLogService.logNoteCreation(userName, note);
        return note;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String userName) {
        Note note = noteRepository.findById(noteId).
                orElseThrow(() -> new RuntimeException("Note not found"));
        note.setContent(content);
        noteRepository.save(note);
        auditLogService.logNoteUpdation(userName, note);
        return note;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String userName) {
        Note note = noteRepository.findById(noteId).orElseThrow(
                () -> new RuntimeException("note not found")
        );
        noteRepository.deleteById(noteId);
        auditLogService.logNoteDeletion(userName, noteId);
    }

    @Override
    public List<Note> getNotesForUser(String userName) {
        return noteRepository.findByOwnerUserName(userName);
    }
}
