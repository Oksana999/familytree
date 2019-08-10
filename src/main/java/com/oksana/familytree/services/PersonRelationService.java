package com.oksana.familytree.services;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import com.oksana.familytree.entity.RelationType;
import com.oksana.familytree.repositories.PersonRelationRepository;
import com.oksana.familytree.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonRelationService {

    private final PersonRelationRepository personRelationRepository;
    private final PersonRepository personRepository;


    public void createRelation(Long id1, Long id2, RelationType relation) {
        PersonRelation personRelation = new PersonRelation();

        Person two = this.personRepository.getOne(id2);
        Person one = this.personRepository.getOne(id1);

        if (RelationType.MARRIAGE.equals(relation)) {
            //TODO
            personRelation.setParent(two);
            personRelation.setChild(one);
        } else {
            int i = two.getBirthday().compareTo(one.getBirthday());
            if (i>0) {
                personRelation.setParent(two);
                personRelation.setChild(one);
            } else {
                personRelation.setParent(one);
                personRelation.setChild(two);
            }
        }


        personRelation.setRelation(relation);
        this.personRelationRepository.save(personRelation);
    }
}
