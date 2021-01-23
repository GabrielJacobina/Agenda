package com.agenda.repositorys;

import com.agenda.models.Contato;
import com.agenda.util.ContatoCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("Tests for Contato Repository")
class ContatoRepositoryTest {

    @Autowired
    private ContatoRepository contatoRepository;

    @Test
    @DisplayName("save persists contato when successful")
    void save_PersistContato_WhenSuccessful() {
        Contato contatoToBeSaved = ContatoCreator.createContato();

        Contato contatoSaved = contatoRepository.save(contatoToBeSaved);

        Assertions.assertThat(contatoSaved).isNotNull();
        Assertions.assertThat(contatoSaved.getNome()).isEqualTo(contatoToBeSaved.getNome());
    }

    @Test
    @DisplayName("save update contato when successful")
    void save_UpdateContato_WhenSuccessful() {
        Contato contatoToBeSaved = ContatoCreator.createContato();

        Contato contatoSaved = contatoRepository.save(contatoToBeSaved);

        contatoSaved.setNome("Leonardo");

        Contato contatoUpdate = contatoRepository.save(contatoSaved);

        Assertions.assertThat(contatoUpdate).isNotNull();
        Assertions.assertThat(contatoUpdate.getNome()).isEqualTo(contatoSaved.getNome());
    }

    @Test
    @DisplayName("delete remove contato when successful")
    void delete_RemoveContato_WhenSuccessful() {
        Contato contatoToBeSaved = ContatoCreator.createContato();

        Contato contatoSaved = contatoRepository.save(contatoToBeSaved);

        contatoRepository.delete(contatoSaved);

        Optional<Contato> contatoOptional = contatoRepository.findById(contatoSaved.getId());

        Assertions.assertThat(contatoOptional).isEmpty();
    }

    @Test
    @DisplayName("save throw ConstraintViolationException when nome is empty")
    void save_ThrowsConstraintViolationException_WhenNomeIsEmpty() {
        Contato contato = new Contato();

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.contatoRepository.save(contato))
                .withMessageContaining("O nome do contato não pode estar vazio");
    }

}