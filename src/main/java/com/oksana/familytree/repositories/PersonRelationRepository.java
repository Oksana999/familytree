package com.oksana.familytree.repositories;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.PersonRelation;
import com.oksana.familytree.entity.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRelationRepository extends JpaRepository<PersonRelation, Long> {
    List<PersonRelation> findAllByParentAndRelation(Person parent, RelationType relation);
    List<PersonRelation> findAllByChildAndRelation(Person child, RelationType relation);
}
