package com.example.simpleboard.crud;

public interface Convertor<DTO, ENTITY> {

    DTO toDto(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
