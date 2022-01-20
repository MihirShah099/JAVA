package com.example.RestApi.prac1.core;

/**
 * here we can define basic mapping strategy,not implemented full functionality..
 *
 * @param <DTO>  DTO model
 * @param <DATA> Data model
 */
public interface BaseMapper<DTO, DATA> {
    DATA dtoToDomain(DTO dto);

    DTO domainToDTO(DATA data);
}
