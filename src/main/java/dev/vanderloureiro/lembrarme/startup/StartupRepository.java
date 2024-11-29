package dev.vanderloureiro.lembrarme.startup;

import org.springframework.data.jpa.repository.JpaRepository;

interface StartupRepository extends JpaRepository<StartupRecord, Long> {}
