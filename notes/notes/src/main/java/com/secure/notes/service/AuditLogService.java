package com.secure.notes.service;

import com.secure.notes.entity.AuditLog;
import com.secure.notes.entity.Note;

import java.util.List;

public interface AuditLogService {
    public void logNoteCreation(String username, Note note);

    public void logNoteUpdation(String username, Note note);

    void logNoteDeletion(String username, Long noteId);

    List<AuditLog> getAllAuditLogs();

    List<AuditLog> getAuditLogForNoteId(String id);
}
