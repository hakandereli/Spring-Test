package com.hd.controller;

import com.hd.dto.PersonDto;
import com.hd.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author hdereli
 * @since 24/7/2023
 */

@RestController
@RequestMapping("api/v1/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDto> save(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.save(personDto));
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }

    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<Void> deleteById(@PathVariable Long personId) {
        personService.delete(personId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{personId}")
    public ResponseEntity<PersonDto> updateById(@PathVariable Long personId, @Valid @RequestBody PersonDto personDto){
        return ResponseEntity.ok(personService.update(personDto));
    }
}
