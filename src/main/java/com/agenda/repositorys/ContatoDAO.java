package com.agenda.repositorys;

import com.agenda.models.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoDAO extends JpaRepository<Contato, Long> {
}
