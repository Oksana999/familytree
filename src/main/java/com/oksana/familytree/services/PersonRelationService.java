package com.oksana.familytree.services;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import com.oksana.familytree.repositories.PersonRelationRepository;
import com.oksana.familytree.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonRelationService {

    private final PersonRelationRepository personRelationRepository;
    private final PersonRepository personRepository;


}
