package dev.vanderloureiro.lembrarme.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM Messages m WHERE m.last_dispatch != m.next_dispatch AND m.next_dispatch = :date", nativeQuery = true)
    List<Message> getAllUnsent(@Param("date") LocalDate date);
}
