package com.hd.service;

import com.hd.dto.PersonDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author hdereli
 * @since 24/7/2023
 */

public interface PersonService {

    PersonDto save(PersonDto personDto);

    void delete(Long id);

    List<PersonDto> findAll();

    PersonDto update(PersonDto personDto);

    Page<PersonDto> findAll(Pageable pageable);
}
