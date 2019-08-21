package com.oksana.familytree.services;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import com.oksana.familytree.entity.Photo;
import com.oksana.familytree.repositories.PersonRelationRepository;
import com.oksana.familytree.repositories.PersonRepository;
import com.oksana.familytree.repositories.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person createOrUpdate(Long id, String name, String surname, String fathername,
                                 LocalDateTime birthday, LocalDateTime deadDay, boolean gender) {
        Person person = (id != null) ? personRepository.getOne(id) : new Person();
        person.setName(name);
        person.setSurname(surname);
        person.setFathername(fathername);
        person.setBirthday(birthday);
        person.setDeadDay(deadDay);
        person.setGender(gender);
        return this.personRepository.save(person);
    }

    public Person update(Person person) {
        return this.personRepository.save(person);
    }

    public Person findByFIO(String name, String surname, String fathername) {
        return this.personRepository.findByNameAndSurnameAndFathername(name, surname, fathername);
    }

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Person findById(Long id) {
        return this.personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person was not found"));
    }

    public void delete(Long id) {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person was not found"));
        this.personRepository.delete(person);

    }
}
