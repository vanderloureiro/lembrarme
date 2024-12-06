package dev.vanderloureiro.lembrarme.startup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface StartupRepository extends JpaRepository<StartupRecord, Long> {}
