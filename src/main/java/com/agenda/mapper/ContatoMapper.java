package com.agenda.mapper;

import com.agenda.dto.ContatoDTO;
import com.agenda.models.Contato;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class ContatoMapper {
    public static final ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

    public abstract Contato toContato(ContatoDTO contatoDTO);
}
