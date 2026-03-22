package com.secure.notes.repository;

import com.secure.notes.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    public List<AuditLog> findByNoteId(String noteId);
}
