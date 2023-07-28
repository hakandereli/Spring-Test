package com.hd.controller;

import com.hd.dto.PersonDto;
import com.hd.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * @author hdereli
 * @since 24/7/2023
 */

@RestController
@RequestMapping("api/v1/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping(path = "/save")
    public ResponseEntity<PersonDto> save(@Valid @RequestBody PersonDto personDto) {
        return ResponseEntity.ok(personService.save(personDto));
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<PersonDto>> findAll() {
        return ResponseEntity.ok(personService.findAll());
    }
}
