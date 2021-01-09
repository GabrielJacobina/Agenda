package com.agenda.controllers;

import com.agenda.services.ContatoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ContatoControllerTest {

    @InjectMocks
    private ContatoController contatoController;

    @Mock
    private ContatoService contatoServiceMock;

    @Test
    void test() {

    }
}