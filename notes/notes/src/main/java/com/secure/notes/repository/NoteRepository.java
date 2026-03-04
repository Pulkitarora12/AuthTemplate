package com.secure.notes.repository;

import com.secure.notes.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwnerUserName(String ownerUsername);
}
