package com.hd.service.impl;

import com.hd.dto.AddressDto;
import com.hd.dto.PersonDto;
import com.hd.entity.Address;
import com.hd.entity.Person;
import com.hd.errorhandler.ResourceNotFoundException;
import com.hd.repo.AddressRepository;
import com.hd.repo.PersonRepository;
import com.hd.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
//        Assert.notNull(personDto.getName(), "Name Field invalid!");

        Person person = convertPersonDtoToPerson(personDto);

        final Person personDb = personRepository.save(person);

        addressRepository.saveAll(personDb.getAdressList());
        personDto.setId(personDb.getId());

        return personDto;
    }

    @Override
    public void delete(Long personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public List<PersonDto> findAll() {
        List<Person> persons = personRepository.findAll();

        List<PersonDto> personDtoList = new ArrayList<>();

        persons.forEach(personDb -> {
            PersonDto personDto =new PersonDto();
            personDto.setId(personDb.getId());
            personDto.setName(personDb.getName());
            personDto.setLastName(personDb.getLastName());
            personDto.setAge(personDb.getAge());
            personDto.setSalary(personDb.getSalary());
            personDto.setEmail(personDb.getEmail());
            personDto.setPhone(personDb.getPhone());
            personDto.setAddressList(
                    personDb.getAdressList() != null ?
                            convertAddressListToAddressDtoList(personDb.getAdressList())
                            : null);
            personDtoList.add(personDto);
        });

        return personDtoList;
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        Person person = convertPersonDtoToPerson(personDto);

        Person updatePerson = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Person not exist with id: " + person.getId()));

        updatePerson.setId(person.getId());
        updatePerson.setName(personDto.getName());
        updatePerson.setLastName(personDto.getLastName());
        updatePerson.setAge(personDto.getAge());
        updatePerson.setEmail(person.getEmail());
        updatePerson.setAdressList(person.getAdressList());
        updatePerson.setSalary(person.getSalary());

        return convertPersonToPersonDto(personRepository.save(updatePerson));
    }

    @Override
    public Page<PersonDto> findAll(Pageable pageable) {
        return null;
    }

    private Person convertPersonDtoToPerson(PersonDto personDto) {

        Person person = new Person();

        person.setId(personDto.getId());
        person.setName(personDto.getName());
        person.setLastName(personDto.getLastName());
        person.setAge(personDto.getAge());
        person.setSalary(personDto.getSalary());
        person.setEmail(personDto.getEmail());
        person.setPhone(personDto.getPhone());
        person.setAdressList(convertAddressDtoListToAddressList(personDto.getAddressList()));

        return person;
    }

    private PersonDto convertPersonToPersonDto(Person person) {

        PersonDto personDto = new PersonDto();

        personDto.setId(personDto.getId());
        personDto.setName(person.getName());
        personDto.setLastName(person.getLastName());
        personDto.setAge(person.getAge());
        personDto.setSalary(person.getSalary());
        personDto.setEmail(person.getEmail());
        personDto.setPhone(person.getPhone());
        personDto.setAddressList(convertAddressListToAddressDtoList(person.getAdressList()));

        return personDto;
    }

    private List<Address> convertAddressDtoListToAddressList(List<AddressDto> addressDtoList) {

        List<Address> addressList = new ArrayList<>();

        for (AddressDto addressDto : addressDtoList) {

            Address address = new Address();

            address.setId(addressDto.getId());
            address.setAddress(addressDto.getAddress());
            address.setActive(addressDto.getActive());
            address.setAdressType(addressDto.getAdressType());
            addressList.add(address);
        }


        return addressList;
    }

    private List<AddressDto> convertAddressListToAddressDtoList(List<Address> addressList) {

        List<AddressDto> addressDtoList = new ArrayList<>();

        for (Address address : addressList) {

            AddressDto addressDto = new AddressDto();

            addressDto.setId(address.getId());
            addressDto.setAddress(address.getAddress());
            addressDto.setActive(address.getActive());
            addressDto.setAdressType(address.getAdressType());
            addressDtoList.add(addressDto);
        }

        return addressDtoList;
    }
}
