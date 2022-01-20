package com.example.eCommerce.prac1.utility.service;

import com.example.eCommerce.prac1.utility.mapper.BaseMapper;
import com.example.eCommerce.prac1.utility.DataNotFoundException;
import com.example.eCommerce.prac1.utility.log.AppLogger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseService<DTO, DATA, ID> implements IBaseService<DTO, ID> {
    private final JpaRepository<DATA, ID> repository;
    private final BaseMapper<DTO, DATA> mapper;

    public BaseService(JpaRepository<DATA, ID> repository, BaseMapper<DTO, DATA> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * used for displaying Module Name in which error has occured..
     *
     * @return String
     */
    public abstract String getModuleName();

    @Override
    public DTO saveEntity(DTO model) throws Exception {
        try {
            DATA domain = mapper.dtoToDomain(model);
            return mapper.domainToDTO(repository.save(domain));
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + "Error while saving data : [ "
                    + model.toString() + "]" + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public DTO updateEntity(DTO model) throws Exception {
        try {
            DATA domain = mapper.dtoToDomain(model);
            return mapper.domainToDTO(repository.save(domain));
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + "Error while updating data : [ "
                    + model.toString() + "]" + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteEntity(DTO model) throws Exception {
        try {
            DATA domain = mapper.dtoToDomain(model);
            repository.delete(domain);
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + "Error while deleting data : [ "
                    + model.toString() + "]" + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public DTO getEntityById(ID id) throws Exception {
        try {
            Optional<DATA> optionalDATA = repository.findById(id);
            DATA domain = optionalDATA.orElse(null);
            if (null == domain)
                throw new DataNotFoundException(getModuleName() + " Data not found for given id : " + id);
            return mapper.domainToDTO(domain);
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<DTO> getAllEntities() throws Exception {
        try {
            return repository.findAll()
                    .stream()
                    .map(mapper::domainToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            AppLogger.logger.error(getModuleName() + "Error while getting dataList" + e.getMessage(), e);
            throw e;
        }
    }
}
