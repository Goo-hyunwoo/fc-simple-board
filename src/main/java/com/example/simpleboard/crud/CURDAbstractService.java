package com.example.simpleboard.crud;

import com.example.simpleboard.common.Api;
import com.example.simpleboard.common.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * dto -> entity -> dto
 */
public abstract class CURDAbstractService<DTO, ENTITY> implements CRUDInterface<DTO> {

    @Autowired(required = false)
    private Convertor<DTO,ENTITY> convertor;

    @Autowired(required = false)
    private JpaRepository<ENTITY, Long> jpaRepository;

    @Override
    public DTO create(DTO dto) {
        // dto -> entity
        var entity = convertor.toEntity((DTO) dto);
        // entity -> save
        jpaRepository.save(entity);
        // save -> dto
        var returnDto = convertor.toDto(entity);
        return returnDto;
    }

    @Override
    public Optional read(Long id) {
        var optionalEntity = jpaRepository.findById(id);
        var dto = optionalEntity
                .map(it -> {
                    return convertor.toDto(it);
                })
                .orElseGet(() -> null);
        return Optional.ofNullable(dto);
    }

    @Override
    public DTO update(DTO dto) {
        var entity = convertor.toEntity(dto);
        jpaRepository.save(entity);
        var returnDto = convertor.toDto(entity);
        return returnDto;
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Api<List<DTO>> list(Pageable pageable) {
        var list = jpaRepository.findAll(pageable);
        var pagination = Pagination.builder()
                .page(list.getNumber())
                .size(list.getSize())
                .currentElements(list.getNumberOfElements())
                .totalElements(list.getTotalElements())
                .totalPage(list.getTotalPages())
                .build();

        var dtoList = list.stream()
                .map(it -> {
                    return convertor.toDto(it);
                })
                .collect(Collectors.toList());
        var response = Api.<List<DTO>>builder()
                .body(dtoList)
                .pagination(pagination)
                .build();
        return response;
    }
}
