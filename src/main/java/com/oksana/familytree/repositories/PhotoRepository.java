package com.oksana.familytree.repositories;

import com.oksana.familytree.entity.Person;
import com.oksana.familytree.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {




}
