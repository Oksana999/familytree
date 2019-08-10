package com.oksana.familytree.repositories;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRelationRepository extends JpaRepository<PersonRelation, Long> {

}
