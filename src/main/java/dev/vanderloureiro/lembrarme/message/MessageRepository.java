package dev.vanderloureiro.lembrarme.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("from Message m where m.lastDispatch != m.nextDispatch AND m.nextDispatch = :date")
    List<Message> getAllUnsent(@Param("date") LocalDate date);
}
