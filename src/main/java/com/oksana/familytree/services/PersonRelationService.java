package com.oksana.familytree.services;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import com.oksana.familytree.entity.RelationType;
import com.oksana.familytree.repositories.PersonRelationRepository;
import com.oksana.familytree.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonRelationService {

    private final PersonRelationRepository personRelationRepository;
    private final PersonRepository personRepository;


    public PersonRelation createRelation(Long id1, Long id2, RelationType relation) {
        PersonRelation personRelation = new PersonRelation();

        Person two = this.personRepository.getOne(id2);
        Person one = this.personRepository.getOne(id1);

        if (RelationType.MARRIAGE.equals(relation)) {
            if (one.getGender().compareTo(two.getGender()) > 0) {
                personRelation.setParent(one);
                personRelation.setChild(two);
            } else {
                personRelation.setParent(two);
                personRelation.setChild(one);
            }
        } else if (two.getBirthday().compareTo(one.getBirthday()) > 0) {
            personRelation.setParent(two);
            personRelation.setChild(one);
        } else {
            personRelation.setParent(one);
            personRelation.setChild(two);
        }


        personRelation.setRelation(relation);
        this.personRelationRepository.save(personRelation);
        return personRelation;
    }

    public List<Person> parentRelations(Long id) {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person was not found"));
        List<Person> parentPersons = new ArrayList<>();
        List<PersonRelation> parents = this.personRelationRepository.findAllByParent(person);
        for (PersonRelation p : parents) {
            Person parent = p.getParent();
            parentPersons.add(parent);
        }
        return parentPersons;
    }

    public List<Person> childRelation(Long id) {
        Person person = this.personRepository.findById(id).orElseThrow(() -> new RuntimeException("Person was not found"));
        List<Person> childPersons = new ArrayList<>();
        List<PersonRelation> children = this.personRelationRepository.findAllByChild(person);
        for (PersonRelation c : children) {
            Person child = c.getChild();
            childPersons.add(child);
        }
        return childPersons;
    }
}
