package com.example.eCommerce.prac1.utility.mapper;

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
