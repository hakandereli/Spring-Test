package com.hd.service.impl;

import com.hd.dto.PersonDto;
import com.hd.entity.Address;
import com.hd.entity.Person;
import com.hd.enums.EnumAddressType;
import com.hd.repo.AddressRepository;
import com.hd.repo.PersonRepository;
import com.hd.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hdereli
 * @since 24/7/2023
 */

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public PersonDto save(PersonDto personDto) {
        Assert.notNull(personDto.getName(), "Name Field invalid!");

        Person person = new Person();
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        final Person personDb = personRepository.save(person);

        List<Address> addressList = new ArrayList<>();
        personDto.getAddressList().forEach(item -> {
            Address address = new Address();
            address.setAddress(item);
            address.setAdressType(EnumAddressType.OTHER);
            address.setActive(true);
            address.setPerson(personDb);
            addressList.add(address);
        });
        addressRepository.saveAll(addressList);
        personDto.setId(personDb.getId());
        return personDto;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> kisiler = personRepository.findAll();
        List<PersonDto> personDtos = new ArrayList<>();

        kisiler.forEach(it -> {
            PersonDto personDto =new PersonDto();
            personDto.setId(it.getId());
            personDto.setName(it.getName());
            personDto.setLastName(it.getLastName());
            personDto.setAddressList(
                    it.getAdressList() != null ?
                    it.getAdressList().stream().map(Address::getAddress).collect(Collectors.toList())
                            : null);
            personDtos.add(personDto);
        });
        return personDtos;
    }

    @Override
    public Page<PersonDto> findAll(Pageable pageable) {
        return null;
    }
}
