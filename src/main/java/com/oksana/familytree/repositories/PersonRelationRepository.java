package com.oksana.familytree.repositories;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRelationRepository extends JpaRepository<PersonRelation, Long> {
    List<PersonRelation> findAllByParent(Person parent);
    List<PersonRelation> findAllByChild(Person child);

}
