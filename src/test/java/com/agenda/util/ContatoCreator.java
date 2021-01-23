package com.agenda.util;

import com.agenda.models.Contato;

public class ContatoCreator {

    public static Contato createContato() {
        return Contato.builder()
                .nome("Lion")
                .telefone("985623479")
                .email("lion@gmail.com")
                .build();
    }
}
